package com.example.hike_menu_calculator.ui.food_list

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
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.databinding.FragmentTypeFoodListBinding
import com.example.hike_menu_calculator.ui.adapters.ListStorageProductsAdapter
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
            viewModel.getAllStorageListFlow().collect {
                val listTypeList = it
                val typeListAdapter = listTypeList.let {
                    ListStorageProductsAdapter(it) { onItemClick(it) }
                }
                launch(Dispatchers.Main) {
                    binding.recyclerViewListProduct.adapter = typeListAdapter
                }

            }
        }


        binding.buttonListProduct.setOnClickListener {
//            findNavController().navigate(
//                R.id.action_food_list_to_listAllProductsFragment
//            )
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

    private fun onItemClick(item: ProductStorage) {
        val bundle = Bundle().apply {
            item.id.let { putInt("storageId", it) }
        }
        findNavController().navigate(
            R.id.action_food_list_to_selectionFoodListFragment,
            bundle
        )
    }
}