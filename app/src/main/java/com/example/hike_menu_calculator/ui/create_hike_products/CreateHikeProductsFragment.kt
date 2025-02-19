package com.example.hike_menu_calculator.ui.create_hike_products

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
import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeProductsInListBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeParticipantsAdapter
import com.example.hike_menu_calculator.ui.adapters.CreateHikeProductsAdapter
import com.example.hike_menu_calculator.ui.create_hike_participant.CreateParticipantDiffUtilCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateHikeProductsFragment : Fragment() {
    private var typeMeal = listOf("Завтрак", "Обед", "Ужин", "Перекус", "Специи")
    lateinit var job: Job

    private var adapter: CreateHikeProductsAdapter? = null

    private var listProduct = listOf<Products>()

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
        job = lifecycleScope.launch(Dispatchers.Main) {
            val listProducts = async(Dispatchers.IO) { viewModel.getAllProductsList() }
            listProduct = listProducts.await()
            adapter = CreateHikeProductsAdapter(listProduct) { onItemClick(it) }
            binding.recyclerViewListProducts.adapter = adapter
        }
        lifecycleScope.launch(Dispatchers.Main) {
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
                runBlocking(Dispatchers.IO) {
                    viewModel.transferTheTripToTheArchive()
                }
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Поход передан в архив",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                findNavController().navigate(
                    R.id.action_createHikeProductsInListFragment_to_this_hike
                )
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
        lifecycleScope.launch(Dispatchers.IO) {
            val listProducts = viewModel.getAllProductsList()
            listProducts.forEach {
                if (item.id == it.id) {
                    if (it.weWillUseItInTheCurrentCampaign) {
                        viewModel.upDateProducts(
                            item.id,
                            item.name,
                            item.weightForPerson,
                            item.packageWeight,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            item.useTheWholePackInOneMeal,
                            false
                        )
                    } else {
                        viewModel.upDateProducts(
                            item.id,
                            item.name,
                            item.weightForPerson,
                            item.packageWeight,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            item.useTheWholePackInOneMeal,
                            true
                        )
                    }
                }
            }
            val newListProducts = viewModel.getAllProductsList()
            launch(Dispatchers.Main) {
                upDateList(newListProducts)
            }
        }
    }

    private fun upDateList(newElement: List<Products>) {
        val result = DiffUtil.calculateDiff(
            CreateProductDiffUtilCallback(
                oldElement = listProduct,
                newElement = newElement
            )
        )
        adapter?.let { adapter ->
            adapter.data = newElement
            result.dispatchUpdatesTo(adapter)
        }
        listProduct = newElement
    }
}