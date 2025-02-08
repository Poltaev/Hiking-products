package com.example.hike_menu_calculator.ui.type_food_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.databinding.FragmentTypeFoodListBinding
import com.example.hike_menu_calculator.ui.adapters.ListTypeProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class FoodListFragment : Fragment() {

    lateinit var job: Job

    private var _binding: FragmentTypeFoodListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: TypeFoodListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return TypeFoodListViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTypeFoodListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllTypeListFlow().collect {
                val listTypeList = it
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
                R.id.action_food_list_to_addingAProductFragment,
                bundle
            )
        }
        binding.buttonAddFood.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("productsId", 9999)
            }
            findNavController().navigate(
                R.id.action_type_food_list_to_viewingTheProductFragment,
                bundle
            )
        }
        binding.buttonAllFood.setOnClickListener {
            findNavController().navigate(
                R.id.action_food_list_to_listAllProductsFragment
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
            R.id.action_type_food_list_to_listProductFragment,
            bundle
        )
    }
}