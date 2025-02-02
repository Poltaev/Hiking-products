package com.example.myapplication.ui.watching_a_single_meal

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.FragmentThisHikeListOfProductsBinding
import com.example.myapplication.databinding.FragmentWatchingASingleMealBinding
import com.example.myapplication.ui.adapters.ThisHikeMenuAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsMenuAdapter
import com.example.myapplication.ui.this_hike_list_of_products.ThisHikeListOfProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.log

class WatchingASingleMealFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentWatchingASingleMealBinding? = null
    private val listProducts = mutableListOf<ThisHikeProducts>()
    private var id = 1
    private val binding get() = _binding!!

    private val viewModel: WatchingASingleMealViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return WatchingASingleMealViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("listTypeId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWatchingASingleMealBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listIdProducts = mutableListOf<Int>()
        val nameParticipant = mutableListOf<String>()

        job = lifecycleScope.launch {
            runBlocking(Dispatchers.IO) {
                viewModel.getAllMenuList().forEach {
                    if (it.mealIntakeId == id) {
                        listIdProducts.add(it.productsId)
                    }
                }
            }
            runBlocking(Dispatchers.IO) {
                viewModel.getAllListFood().forEach { item1 ->
                    listIdProducts.forEach {
                        if (item1.id == it) {
                            listProducts.add(item1)
                        }
                    }
                }
            }
            runBlocking(Dispatchers.IO) {
                val item = viewModel.getAllProductsParticipant()
                listProducts.forEach { itemProduct ->
                    item.forEach { itemProductParticipant ->
                        if (itemProduct.id == itemProductParticipant.productsId) {
                            val participantList = viewModel.getAllPartisipant()
                            participantList.forEach { itemParticipant ->
                                if (itemProductParticipant.participantId == itemParticipant.id) {
                                    nameParticipant.add(itemParticipant.firstName + " " + itemParticipant.lastName)
                                }
                            }
                        }
                    }
                }
            }
            val typeListAdapter = listProducts.let {
                ThisHikeProductsMenuAdapter(it, nameParticipant) { onItemClick(it) }
            }
            binding.recyclerViewListEating.adapter = typeListAdapter
            binding.buttonEatAMeal.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    listProducts.forEach {
                        viewModel.upDateProducts(
                            it.id,
                            it.name,
                            it.weightForPerson,
                            it.packageWeight,
                            it.theWeightOfOneMeal,
                            it.weightOnTheHike,
                            it.remainingWeight - it.theWeightOfOneMeal,
                            it.partiallyAssembled,
                            it.fullyAssembled,
                            it.theVolumeItem,
                            it.theSoleOwner,
                            it.nameOwner,
                            it.idOwner,
                            it.useTheWholePackInOneMeal,
                            it.comment
                        )
                    }
                    listProducts.forEach {
                        if (it.remainingWeight - it.theWeightOfOneMeal <= 0) {
                            viewModel.getAllProductsParticipant()
                                .forEach { itemParticipantProduct ->
                                    if (it.id == itemParticipantProduct.productsId) {
                                        viewModel.deleteParticipantProducts(itemParticipantProduct)
                                        viewModel.getAllPartisipant().forEach { itemParticipant ->
                                            if (itemParticipantProduct.participantId == itemParticipant.id) {
                                                viewModel.upDateParticipant(
                                                    itemParticipant.id,
                                                    itemParticipant.hikeId,
                                                    itemParticipant.photo,
                                                    itemParticipant.firstName,
                                                    itemParticipant.lastName,
                                                    itemParticipant.gender,
                                                    itemParticipant.age,
                                                    itemParticipant.maximumPortableWeight,
                                                    itemParticipant.weightOfPersonalItems,
                                                    itemParticipant.weightWithLoad - it.theWeightOfOneMeal,
                                                    itemParticipant.comment
                                                )
                                            }
                                        }
                                    }
                                }
                            viewModel.getAllThisHikeProductsMealList().forEach { itemProductMeal ->
                                if (it.id == itemProductMeal.productsId) {
                                    viewModel.deleteMealProducts(itemProductMeal)
                                    viewModel.getAllThisHikeMealIntakeSheet().forEach { itemMeal ->
                                        if (itemMeal.id == itemProductMeal.mealIntakeId) {
                                            viewModel.deleteMealProducts(itemMeal)
                                        }
                                    }
                                }
                            }
                            viewModel.deleteProducts(it)
                        }
                    }
                }
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Еда съедена, милорд!",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                binding.buttonEatAMeal.isClickable = false
            }
        }

    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(item: ThisHikeProducts) {
        val bundle = Bundle().apply {
            item.id.let { putInt("listTypeId", it) }
        }
    }
}