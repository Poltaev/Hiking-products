package com.example.myapplication.ui.add_products_in_list

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
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.FragmentAddProductsInListBinding
import com.example.myapplication.databinding.FragmentListAllProductsBinding
import com.example.myapplication.ui.adapters.ListAddProductsAdapter
import com.example.myapplication.ui.adapters.ListProductsAdapter
import com.example.myapplication.ui.adapters.ListProductsInTypeListProductAdapter
import com.example.myapplication.ui.list_all_products.ListAllProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddProductsInListFragment : Fragment() {

    lateinit var job: Job

    private var id = 1

    private var listIdProducts = mutableListOf<Int>()

    private var _binding: FragmentAddProductsInListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AddProductsInListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddProductsInListViewModel(hikeDao) as T
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
        _binding = FragmentAddProductsInListBinding.inflate(inflater, container, false)
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
                putInt("listTypeId", id)
            }
            findNavController().navigate(
                R.id.action_addProductsInListFragment_to_listProductFragment,
                bundle
            )
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val listTypeOfProduct = viewModel.getAllListTypeOfProduct()
            listTypeOfProduct.forEach {
                if (it.id == id) {
                    binding.textViewHeadingNameList.text =
                        "Для добавления в: " + it.typeOfMeal + " " + it.name
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
           launch(Dispatchers.IO) {
               val listWithProduct = viewModel.getAllListWithProduct()
               listWithProduct.forEach {
                   if (it.listId == id) {
                       listIdProducts.add(it.productsId)
                   }
               }
           }
           viewModel.getAllProductFlow().collect {
               val getProductList = it
               val ProductAdapter =
                   getProductList.let {
                       ListAddProductsAdapter(
                           it,
                           listIdProducts
                       ) { onItemClick(it) }
                   }
               binding.recyclerViewListProducts.adapter = ProductAdapter
           }
       }
    }


    private fun onItemClick(item: Products) {
        var weNaveIt = true
        listIdProducts.forEach {
            if (item.id == it) {
                weNaveIt = false
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteProductsWithList(ListProducts(id, item.id))
                }
            }
        }
        if (weNaveIt){}
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.addProductsWithList(id, item.id)
        }
        listIdProducts.add(item.id)
        lifecycleScope.launch {
            checkAndUpDateTheList()
        }
    }
}