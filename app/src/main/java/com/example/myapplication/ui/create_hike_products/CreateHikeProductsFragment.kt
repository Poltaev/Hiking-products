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
    private val idThisHikeProduct = mutableListOf<Int>()
    private val idProduct = mutableListOf<Int>()
    private val idListTypeProduct = mutableListOf<Int>()
    private var numberOfDay = 0
    private var numberOfSnacks = 0
    private var numberOfParticipants = 0
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
                numberOfDay = viewModel.getAllThisHike()[0].numberOfDay
                numberOfSnacks = viewModel.getAllThisHike()[0].numberOfSnacksInDay
                numberOfParticipants = viewModel.getAllParticipants().size
                var counter = 1
                for (x in 1..numberOfDay) {
                    typeMeal.forEach {
                        if (it == "Перекус") {
                            if (numberOfSnacks != 0) {
                                for (y in 1..numberOfSnacks) {
                                    viewModel.createMealSheetProducts(
                                        counter,
                                        1,
                                        "${it} ${y} День ${x}",
                                        x,
                                        it
                                    )
                                    counter++
                                }
                            }
                            counter++
                        } else {
                            viewModel.createMealSheetProducts(
                                counter,
                                1,
                                "${it} День ${x}",
                                x,
                                it
                            )
                            counter++
                        }
                    }
                }
                viewModel.getAllThisHikeProductsList().forEach {
                    idThisHikeProduct.add(it.id)
                }
                typeMeal.forEach { typeMeal ->
                    viewModel.getAllListProductsList().forEach { item ->
                        if (item.typeOfMeal == typeMeal) {
                            idListTypeProduct.add(item.id)
                        }
                    }
                    idListTypeProduct.forEach {
                        viewModel.getAllListProducts().forEach { item ->
                            if (item.listId == it) {
                                idProduct.add(item.productsId)
                            }
                        }
                        idProduct.forEach {
                            if (idThisHikeProduct.contains(it) == false) {
                                idProduct.remove(it)
                            }
                        }
                        idProduct.shuffle()
                        val thisMealProducts = mutableListOf<ThisHikeProducts>()
                        viewModel.getAllThisHikeProductsList().forEach { item ->
                            idProduct.forEach {
                                if (item.id == it) {
                                    thisMealProducts.add(item)
                                }
                            }
                        }
                        idProduct.clear()
                        var couterProducts = 0
                        for (x in 1..numberOfDay) {
                            if (couterProducts > thisMealProducts.size - 1) {
                                couterProducts = 0
                            }
                            val theWeightOfOneMeal =
                                thisMealProducts[couterProducts].weightForPerson * numberOfParticipants
                            val weightOnTheHike =
                                thisMealProducts[couterProducts].weightOnTheHike + theWeightOfOneMeal
                            viewModel.upDateThisHikeProducts(
                                thisMealProducts[couterProducts].id,
                                thisMealProducts[couterProducts].name,
                                thisMealProducts[couterProducts].weightForPerson,
                                thisMealProducts[couterProducts].packageWeight,
                                theWeightOfOneMeal,
                                weightOnTheHike,
                                weightOnTheHike,
                                thisMealProducts[couterProducts].partiallyAssembled,
                                thisMealProducts[couterProducts].fullyAssembled,
                                thisMealProducts[couterProducts].theVolumeItem,
                                thisMealProducts[couterProducts].comment
                            )
                            var idMealList = 1
                            viewModel.getAllThisHikeMealIntakeSheet().forEach {
                                if (it.numberOfday == x){
                                    if(it.typeMeal == typeMeal){
                                        idMealList = it.id
                                    }
                                }
                            }
                            viewModel.createThisHikeProductsMealList(
                                idMealList,
                                thisMealProducts[couterProducts].id
                            )
                            couterProducts++
                        }
                    }
                }
            }
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