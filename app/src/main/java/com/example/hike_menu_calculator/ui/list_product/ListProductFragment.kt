package com.example.hike_menu_calculator.ui.list_product

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
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.databinding.FragmentListProductBinding
import com.example.hike_menu_calculator.ui.adapters.ListProductsInTypeListProductAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListProductFragment : Fragment() {


    private var id = 1

    private var _binding: FragmentListProductBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ListProductViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ListProductViewModel(hikeDao) as T
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
        _binding = FragmentListProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val listTypeOfProduct = viewModel.getAllListTypeOfProduct()
            listTypeOfProduct.forEach {
                if (it.id == id) {
                    binding.textViewHeadingList.text =
                        "Список продуктов в " + it.typeOfMeal + " " + it.name
                }
            }
            val listWithProduct = viewModel.getAllListWithProduct()
            val idListProductsforAdapter = mutableListOf<Int>()
            listWithProduct.forEach {
                if (it.listId == id) {
                    idListProductsforAdapter.add(it.productsId)
                }
            }

        launch(Dispatchers.Main) {
            delay(100)
            viewModel.getAllProductFlow().collect {
                val getProductList = it
                val listProductsforAdapter = mutableListOf<Products>()
                for (i in 0..idListProductsforAdapter.size - 1) {
                    getProductList.forEach {
                        if (idListProductsforAdapter[i] == it.id) {
                            listProductsforAdapter.add(it)
                        }
                    }
                }
                val ProductAdapter =
                    listProductsforAdapter.let { ListProductsInTypeListProductAdapter(it) { onItemClick(it) } }
                binding.recyclerViewListWithProducts.adapter = ProductAdapter
            }
        }
    }
            binding.buttonShangeListFood.setOnClickListener {
                val bundle = Bundle().apply {
                    putInt("listTypeId", id)
                }
                findNavController().navigate(
                    R.id.action_listProductFragment_to_addingATypeListProductFragment,
                    bundle
                )
            }
        binding.buttonAddFood.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("listTypeId", id)
            }
            findNavController().navigate(
                R.id.action_listProductFragment_to_addProductsInListFragment,
                bundle
            )
        }
            binding.buttonDeleteListProducts.setOnClickListener {
                if (id != 9999) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val listTypeOfProducts = viewModel.getAllListTypeOfProduct()
                        listTypeOfProducts.forEach {
                            if (it.id == id) {
                                viewModel.deleteListTypeOfProducts(it)
                            }
                        }
                    }

                    val toast = Toast.makeText(
                        requireContext().applicationContext,
                        "Список продуктов удален",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                } else {
                    val toast = Toast.makeText(
                        requireContext().applicationContext,
                        "Список продуктов для удаления не выбран",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                findNavController().navigate(
                    R.id.action_listProductFragment_to_food_list
                )
            }

    }

    override fun onPause() {
        super.onPause()
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
            R.id.action_listProductFragment_to_viewingTheProductFragment,
            bundle
        )
    }
}