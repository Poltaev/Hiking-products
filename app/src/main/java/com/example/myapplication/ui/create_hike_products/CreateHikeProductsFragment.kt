package com.example.myapplication.ui.create_hike_products

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
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
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.FragmentCreateHikeProductsInListBinding
import com.example.myapplication.ui.adapters.CreateHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.CreateHikeProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeProductsFragment : Fragment() {

    lateinit var job: Job

    private val listId = mutableListOf<Int>()

    private var _binding: FragmentCreateHikeProductsInListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeProductsiewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeProductsiewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val listProducts = viewModel.getAllProductsList()
            listProducts.forEach {
                if (it.weWillUseItInTheCurrentCampaign) {
                    listId.add(id)
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateHikeProductsInListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndUpDateTheList()
        binding.buttonFurther.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listProducts = viewModel.getAllProductsList()
                listProducts.forEach {
                    if (it.weWillUseItInTheCurrentCampaign) {
                        viewModel.createHikeProducts(
                            it.id,
                            it.name,
                            it.weightForPerson,
                            it.packageWeight,
                            0,
                            0,
                            0,
                            false,
                            false,
                            false,
                            ""
                        )
                    }
                }
            }
            findNavController().navigate(
                R.id.action_createHikeProductsInListFragment_to_createHikeListProductsFragment
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
                Log.i("Size listid", "${it[0].weWillUseItInTheCurrentCampaign}")
                val ProductsAdapter =
                    getProductsList.let {
                        CreateHikeProductsAdapter(
                            it,
                            listId
                        ) { onItemClick(it) }
                    }
                binding.recyclerViewListProducts.adapter = ProductsAdapter
            }
        }
    }

    private fun onItemClick(item: Products) {
        var weNaveIt = true
        var remove = 0
        listId.forEach {
            if (item.id == it) {
                weNaveIt = false
                remove = it
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
            }
        }
        listId.remove(remove)
        if (weNaveIt) {
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
            listId.add(item.id)
        }
        job.cancel()
        checkAndUpDateTheList()
    }
}