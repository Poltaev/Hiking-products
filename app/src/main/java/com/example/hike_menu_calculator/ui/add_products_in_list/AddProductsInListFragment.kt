package com.example.hike_menu_calculator.ui.add_products_in_list

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
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.databinding.FragmentAddProductsInListBinding
import com.example.hike_menu_calculator.ui.adapters.ListAddProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddProductsInListFragment : Fragment() {

    lateinit var job: Job

    private var id = 1

    private val listIdProducts = mutableListOf<Int>()

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
        lifecycleScope.launch(Dispatchers.IO) {
                val listWithProduct = viewModel.getAllListWithProduct()
                listWithProduct.forEach {
                    if (it.listId == id) {
                        listIdProducts.add(it.productsId)
                }
            }
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
                    launch(Dispatchers.Main) {
                        binding.textViewHeadingNameList.text =
                            "Для добавления в: " + it.typeOfMeal + " " + it.name
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
           viewModel.getAllProductFlow().collect {
               delay(100)
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
                viewModel.addProductsWithList(id, item.id)
            }
            listIdProducts.add(item.id)
        }
        job.cancel()
        lifecycleScope.launch {
            checkAndUpDateTheList()
        }
    }
}