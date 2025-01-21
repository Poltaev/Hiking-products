package com.example.myapplication.ui.watching_a_single_meal

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
import kotlinx.coroutines.launch

class WatchingASingleMealFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentWatchingASingleMealBinding? = null
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

        job = lifecycleScope.launch(Dispatchers.IO) {
            val listIdProducts = mutableListOf<Int>()
            val listProducts = mutableListOf<ThisHikeProducts>()
            viewModel.getAllMenuListFlow().collect {
                it.forEach {
                    if (it.mealIntakeId == id){
                        listIdProducts.add(it.productsId)
                    }
                }
                viewModel.getAllListFoodFlow().collect{x ->
                    listIdProducts.forEach {
                        for (y in 0..x.size-1){
                            if (it == x[y].id) listProducts.add(x[y])
                        }
                    }
                }
                val typeListAdapter = listProducts.let {
                    ThisHikeProductsMenuAdapter(it){onItemClick(it)}
                }
                binding.recyclerViewListEating.adapter = typeListAdapter
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
//        findNavController().navigate(
//            R.id.action_viewTheMenuFragment_to_watchingASingleMealFragment,
//            bundle
//        )
    }
}