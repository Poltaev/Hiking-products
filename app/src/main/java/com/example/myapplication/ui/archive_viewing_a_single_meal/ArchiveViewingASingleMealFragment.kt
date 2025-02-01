package com.example.myapplication.ui.archive_viewing_a_single_meal

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.dataBase.archive.ArchiveProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.FragmentArchiveViewingASingleMealBinding
import com.example.myapplication.databinding.FragmentWatchingASingleMealBinding
import com.example.myapplication.ui.adapters.ArchiveHikeProductsMenuAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsMenuAdapter
import com.example.myapplication.ui.watching_a_single_meal.WatchingASingleMealViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArchiveViewingASingleMealFragment : Fragment() {

    lateinit var job: Job
    private var _binding: FragmentArchiveViewingASingleMealBinding? = null
    private val listProducts = mutableListOf<ArchiveProducts>()
    private var id = 1
    private val binding get() = _binding!!

    private val viewModel: ArchiveViewingASingleMealViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveViewingASingleMealViewModel(hikeDao) as T
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
        _binding = FragmentArchiveViewingASingleMealBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listIdProducts = mutableListOf<Int>()
        val nameParticipant = mutableListOf<String>()

        job = lifecycleScope.launch {
            runBlocking(Dispatchers.IO) {
                viewModel.getAllMenuList().forEach {
                    if (it.mealIntakeId == id) {
                        listIdProducts.add(it.productsId)
                    }
                }
            }
            runBlocking(Dispatchers.IO) {
                viewModel.getAllListFood().forEach { item1 ->
                    listIdProducts.forEach {
                        if (item1.id == it) {
                            listProducts.add(item1)
                        }
                    }
                }
            }
            runBlocking(Dispatchers.IO) {
                val item = viewModel.getAllProductsParticipant()
                listProducts.forEach { itemProduct ->
                    item.forEach { itemProductParticipant ->
                        if (itemProduct.id == itemProductParticipant.productsId) {
                            val participantList = viewModel.getAllPartisipant()
                            participantList.forEach { itemParticipant ->
                                if (itemProductParticipant.participantId == itemParticipant.id) {
                                    nameParticipant.add(itemParticipant.firstName + " " + itemParticipant.lastName)
                                }
                            }
                        }
                    }
                }
            }
            val typeListAdapter = listProducts.let {
                ArchiveHikeProductsMenuAdapter(it, nameParticipant)
            }
            binding.recyclerViewListEating.adapter = typeListAdapter
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