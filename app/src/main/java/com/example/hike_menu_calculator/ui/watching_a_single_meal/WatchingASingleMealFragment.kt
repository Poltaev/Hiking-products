package com.example.hike_menu_calculator.ui.watching_a_single_meal

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.FragmentWatchingASingleMealBinding
import com.example.hike_menu_calculator.ui.adapters.ThisHikeProductsMenuAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
                viewModel.getAllThisHikeListIdProductsInMeal().forEach {
                    if (it.idMeal == id) {
                        listIdProducts.add(it.idProductsInMeal)
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
            launch(Dispatchers.Main) {
                binding.recyclerViewListEating.adapter = typeListAdapter
            }
            binding.buttonEatAMeal.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    listProducts.forEach {
                        var thisProduct = it
                        runBlocking {
                            viewModel.getAllListFood().forEach { item ->
                                if (it.id == item.id) {
                                    thisProduct = item
                                }
                            }
                        }
                        viewModel.upDateProducts(
                            thisProduct.id,
                            thisProduct.name,
                            thisProduct.weightForPerson,
                            thisProduct.packageWeight,
                            thisProduct.theWeightOfOneMeal,
                            thisProduct.weightOnTheHike,
                            thisProduct.remainingWeight - thisProduct.theWeightOfOneMeal,
                            thisProduct.partiallyAssembled,
                            thisProduct.fullyAssembled,
                            thisProduct.theVolumeItem,
                            thisProduct.theSoleOwner,
                            thisProduct.nameOwner,
                            thisProduct.idOwner,
                            thisProduct.useTheWholePackInOneMeal,
                            thisProduct.comment
                        )
                    }
                    listProducts.forEach {
                        if (it.remainingWeight - it.theWeightOfOneMeal <= 0) {
                            viewModel.getAllProductsParticipant()
                                .forEach { itemParticipantProduct ->
                                    if (it.id == itemParticipantProduct.productsId) {
                                        viewModel.deleteParticipantProducts(
                                            itemParticipantProduct
                                        )
                                        viewModel.getAllPartisipant()
                                            .forEach { itemParticipant ->
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
                            viewModel.getAllThisHikeProductsMealList()
                                .forEach { itemProductMeal ->
                                    if (it.id == itemProductMeal.productsId) {
                                        viewModel.deleteMealProducts(itemProductMeal)
                                        viewModel.getAllThisHikeMealIntakeSheet()
                                            .forEach { itemMeal ->
                                                if (itemMeal.id == itemProductMeal.mealIntakeId) {
                                                    viewModel.deleteMealProducts(itemMeal)
                                                }
                                            }
                                    }
                                }
                            viewModel.deleteProducts(it)
                        }
                    }
                    viewModel.getAllListFood().forEach {
                        if (it.remainingWeight == 0) {
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