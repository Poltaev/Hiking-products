package com.example.hike_menu_calculator.ui.this_hike_list_of_products

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.FragmentThisHikeListOfProductsBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeEquipmentAdapter
import com.example.hike_menu_calculator.ui.adapters.ThisHikeProductsAdapter
import com.example.hike_menu_calculator.ui.create_hike_equipment.CreateEquipmentDiffUtilCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ThisHikeListOfProductsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeListOfProductsBinding? = null

    private val binding get() = _binding!!

    private var adapter: ThisHikeProductsAdapter? = null

    private var listThisHikeProducts = listOf<ThisHikeProducts>()

    private val viewModel: ThisHikeListOfProductsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeListOfProductsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThisHikeListOfProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.IO) {
                 listThisHikeProducts = viewModel.getAllProducts()
                var nameParticipant = listOf<String>()
                runBlocking(Dispatchers.IO) {
                    nameParticipant = viewModel.getNameParticipant(listThisHikeProducts)
                }

                adapter = ThisHikeProductsAdapter(
                    listThisHikeProducts,
                    nameParticipant,
                    { onItemClick(it) },
                    { onItemClickCollected(it) },
                    { onItemClickHandOver(it) })

                launch(Dispatchers.Main) {
                    binding.recyclerViewListProducts.adapter = adapter
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
            item.id.let { putInt("productTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_thisHikeListOfProductsFragment_to_addCommentFragment,
            bundle
        )
    }

    private fun onItemClickCollected(item: ThisHikeProducts) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listThisHikeProducts = viewModel.getAllProducts()
            listThisHikeProducts.forEach {
                if (item.id == it.id) {
                    if (it.fullyAssembled) {
                        viewModel.upDateThisHikeProducts(
                            item.id,
                            item.name,
                            item.weightForPerson,
                            item.packageWeight,
                            item.theWeightOfOneMeal,
                            item.weightOnTheHike,
                            item.remainingWeight,
                            item.partiallyAssembled,
                            false,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            item.useTheWholePackInOneMeal,
                            item.comment
                        )
                        launch(Dispatchers.Main) {
                            val toast = Toast.makeText(
                                requireContext().applicationContext,
                                "Продукт не собран",
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                        }
                    } else {
                        viewModel.upDateThisHikeProducts(
                            item.id,
                            item.name,
                            item.weightForPerson,
                            item.packageWeight,
                            item.theWeightOfOneMeal,
                            item.weightOnTheHike,
                            item.remainingWeight,
                            item.partiallyAssembled,
                            true,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            item.useTheWholePackInOneMeal,
                            item.comment
                        )
                        launch(Dispatchers.Main) {
                            val toast = Toast.makeText(
                                requireContext().applicationContext,
                                "Продукт собран",
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                        }
                    }
                }
            }
            val newListThisHikeProducts = viewModel.getAllProducts()
            launch(Dispatchers.Main) {
                upDateList(newListThisHikeProducts)
            }
        }
    }

    private fun onItemClickHandOver(item: ThisHikeProducts) {
        val bundle = Bundle().apply {
            item.id.let { putInt("productTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_thisHikeListOfProductsFragment_to_passOnOneThingFragment,
            bundle
        )
    }

    private fun upDateList(newElement: List<ThisHikeProducts>) {
        val result = DiffUtil.calculateDiff(
            ThisHikeListOfProductDiffUtilCallback(
                oldElement = listThisHikeProducts,
                newElement = newElement
            )
        )
        adapter?.let { adapter ->
            adapter.data = newElement
            result.dispatchUpdatesTo(adapter)
        }
        listThisHikeProducts = newElement
    }
}