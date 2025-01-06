package com.example.myapplication.ui.list_all_products

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
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.FragmentAddingATypeListProductBinding
import com.example.myapplication.databinding.FragmentListAllProductsBinding
import com.example.myapplication.ui.adapters.ListEquipmentAdapter
import com.example.myapplication.ui.adapters.ListProductsAdapter
import com.example.myapplication.ui.adding_a_type_list_product.AddingATypeListProductViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ListAllProductsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentListAllProductsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ListAllProductsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ListAllProductsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListAllProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllProductFlow().collect {
                val getProductList = it
                val ProductAdapter =
                    getProductList.let { ListProductsAdapter(it) { onItemClick(it) } }
                binding.recyclerViewListProducts.adapter = ProductAdapter
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

    private fun onItemClick(item: Products) {
        val bundle = Bundle().apply {
            item.id.let { putInt("productsId", it) }
        }
        findNavController().navigate(
            R.id.action_listAllProductsFragment_to_viewingTheProductFragment,
            bundle
        )
    }
}