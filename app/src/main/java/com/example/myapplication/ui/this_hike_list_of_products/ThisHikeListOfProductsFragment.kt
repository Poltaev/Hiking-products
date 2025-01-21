package com.example.myapplication.ui.this_hike_list_of_products

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
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.FragmentThisHikeListOfProductsBinding
import com.example.myapplication.ui.adapters.ThisHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ThisHikeListOfProductsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeListOfProductsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeListOfProductsViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeListOfProductsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThisHikeListOfProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllProductsFlow().collect {
                val listProducts = it
                val typeListAdapter = listProducts.let {
                    ThisHikeProductsAdapter(it)
                }
                binding.recyclerViewListProducts.adapter = typeListAdapter
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

}