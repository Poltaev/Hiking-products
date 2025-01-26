package com.example.myapplication.ui.create_hike_products

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.FragmentCreateHikeProductsInListBinding
import com.example.myapplication.ui.adapters.CreateHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.CreateHikeProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeProductsFragment : Fragment() {
    private var typeMeal = listOf("Завтрак", "Обед", "Ужин", "Перекус", "Специи")
    lateinit var job: Job

    private var _binding: FragmentCreateHikeProductsInListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeProductsiewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeProductsiewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateHikeProductsInListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndUpDateTheList()
        binding.buttonCreateAHike.setOnClickListener {
            viewModel.createAHikeProducts(typeMeal)
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Продукты в поход добавлены",
                Toast.LENGTH_SHORT
            )
            toast.show()
            binding.buttonCreateAHike.isEnabled = false
        }

        binding.buttonFurther.setOnClickListener {
            findNavController().navigate(
                R.id.action_createHikeProductsInListFragment_to_this_hike
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

    private fun checkAndUpDateTheList() {
        job = lifecycleScope.launch {
            delay(100)
            viewModel.getAllProductsFlow().collect {
                delay(100)
                val getProductsList = it
                val ProductsAdapter =
                    getProductsList.let {
                        CreateHikeProductsAdapter(
                            it
                        ) { onItemClick(it) }
                    }
                binding.recyclerViewListProducts.adapter = ProductsAdapter
            }
        }
    }

    private fun onItemClick(item: Products) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listProduct = viewModel.getAllProductsList()
            listProduct.forEach {
                if (item.id == it.id) {
                    if (it.weWillUseItInTheCurrentCampaign) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateProducts(
                                item.id,
                                item.name,
                                item.weightForPerson,
                                item.packageWeight,
                                item.theVolumeItem,
                                false
                            )
                        }
                    } else {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateProducts(
                                item.id,
                                item.name,
                                item.weightForPerson,
                                item.packageWeight,
                                item.theVolumeItem,
                                true
                            )
                        }
                    }
                }
            }
        }
        job.cancel()
        checkAndUpDateTheList()
    }
}