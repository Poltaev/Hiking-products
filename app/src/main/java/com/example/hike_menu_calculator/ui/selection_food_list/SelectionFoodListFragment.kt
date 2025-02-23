package com.example.hike_menu_calculator.ui.selection_food_list

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.databinding.FragmentSelectionFoodListBinding
import com.example.hike_menu_calculator.databinding.FragmentTypeFoodListBinding
import com.example.hike_menu_calculator.ui.adapters.ListTypeProductsAdapter
import com.example.hike_menu_calculator.ui.food_list.TypeFoodListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SelectionFoodListFragment : Fragment() {


    lateinit var job: Job

    private var _binding: FragmentSelectionFoodListBinding? = null

    private val binding get() = _binding!!
    private var idStorage = 1
    private val viewModel: SelectionFoodListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return SelectionFoodListViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idStorage = it.getInt("storageId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionFoodListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllTypeListFlow().collect {
                val listTypeList = mutableListOf<ListTypeOfProducts>()
                it.forEach { item ->
                    if(item.idProductStorage == idStorage){
                        listTypeList.add(item)
                    }
                }
                val typeListAdapter = listTypeList.let {
                    ListTypeProductsAdapter(it) { onItemClick(it) }
                }
                launch(Dispatchers.Main) {
                    binding.recyclerViewTypeListProducts.adapter = typeListAdapter
                }

            }
        }
        binding.buttonAddList.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("listTypeId", 9999)
            }
            findNavController().navigate(
                R.id.action_selectionFoodListFragment_to_addingATypeListProductFragment,
                bundle
            )
        }
        binding.buttonAddFood.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("productsId", 9999)
            }
            findNavController().navigate(
                R.id.action_selectionFoodListFragment_to_viewingTheProductFragment,
                bundle
            )
        }
        binding.buttonAllFood.setOnClickListener {
            findNavController().navigate(
                R.id.action_selectionFoodListFragment_to_listAllProductsFragment
            )
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

    private fun onItemClick(item: ListTypeOfProducts) {
        val bundle = Bundle().apply {
            item.id.let { putInt("listTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_selectionFoodListFragment_to_listProductFragment,
            bundle
        )
    }
}