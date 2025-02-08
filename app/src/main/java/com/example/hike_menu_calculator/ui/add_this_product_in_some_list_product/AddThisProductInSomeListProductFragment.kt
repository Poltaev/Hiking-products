package com.example.hike_menu_calculator.ui.add_this_product_in_some_list_product

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
import com.example.hike_menu_calculator.dataBase.products.ListProducts
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.databinding.FragmentAddThisProductInSomeListProductBinding
import com.example.hike_menu_calculator.ui.adapters.ListAddProductsInListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddThisProductInSomeListProductFragment : Fragment() {

    lateinit var job: Job

    private var id = 1

    private val listIdProducts = mutableListOf<Int>()

    private var _binding: FragmentAddThisProductInSomeListProductBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AddThisProductInSomeListProductViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddThisProductInSomeListProductViewModel(hikeDao) as T
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("productsId")
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val listWithProduct = viewModel.getAllListWithProduct()
            listWithProduct.forEach {
                if (it.productsId == id) {
                    listIdProducts.add(it.listId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddThisProductInSomeListProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            checkAndUpDateTheList()
        }
        binding.buttonSave.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("productsId", id)
            }
            findNavController().navigate(
                R.id.action_addThisProductInSomeListProductFragment_to_viewingTheProductFragment,
                bundle
            )
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val listProduct = viewModel.getAllProductList()
            listProduct.forEach {
                if (it.id == id) {
                    launch(Dispatchers.Main) {
                        binding.textViewHeadingNameList.text =
                            "Продукт для добавления: " + it.name
                    }
                }
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
    suspend private fun checkAndUpDateTheList() {
        job = lifecycleScope.launch {
            viewModel.getAllListTypeOfProductFlow().collect {
                delay(100)
                val getProductList = it
                val ProductAdapter =
                    getProductList.let {
                        ListAddProductsInListAdapter(
                            it,
                            listIdProducts
                        ) { onItemClick(it) }
                    }
                binding.recyclerViewListProducts.adapter = ProductAdapter
            }
        }
    }
    private fun onItemClick(item: ListTypeOfProducts) {
        var weNaveIt = true
        var remove = 0
        listIdProducts.forEach {
            if (item.id == it) {
                weNaveIt = false
                remove = it
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteProductsWithList(ListProducts(id, item.id))
                }
            }
        }
        listIdProducts.remove(remove)
        if (weNaveIt){
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.addProductsWithList(item.id, id)
            }
            listIdProducts.add(item.id)
        }
        job.cancel()
        lifecycleScope.launch {
            checkAndUpDateTheList()
        }
    }
}