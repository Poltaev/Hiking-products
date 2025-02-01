package com.example.myapplication.ui.archive_hike_products

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
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.archive.ArchiveProducts
import com.example.myapplication.databinding.FragmentArchiveHikeEquipmentBinding
import com.example.myapplication.databinding.FragmentArchiveHikeProductsBinding
import com.example.myapplication.ui.adapters.ArchiveHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.ArchiveHikeProductsAdapter
import com.example.myapplication.ui.adapters.StorageArchiveAdapterAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsAdapter
import com.example.myapplication.ui.archive_hike_equipment.ArchiveHikeEquipmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArchiveHikeProductsFragment : Fragment() {
    lateinit var job: Job
    private var id = 0
    private var _binding: FragmentArchiveHikeProductsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ArchiveHikeProductsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveHikeProductsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("archiveHike")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArchiveHikeProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllArchiveHikeListProductsFlow().collect {
                val listProducts = mutableListOf<ArchiveProducts>()
                val nameParticipant = mutableListOf<String>()
                val listParticipant = mutableListOf<ArchiveParticipants>()
                runBlocking(Dispatchers.IO) {
                    viewModel.getAllArchiveHikeListParticipant()
                        .forEach { archiveHikelistParticipant ->
                            if (archiveHikelistParticipant.hikeId == id) {
                                listParticipant.add(archiveHikelistParticipant)
                            }
                        }
                }
                runBlocking(Dispatchers.IO) {
                    listParticipant.forEach { paticipant ->
                        viewModel.getAllProductsParticipant().forEach { productsParticipant ->
                            if (paticipant.id == productsParticipant.participantId){
                                it.forEach {
                                    if (it.id == productsParticipant.productsId){
                                        listProducts.add(it)
                                    }
                                }
                            }
                        }
                    }
                }
                runBlocking(Dispatchers.IO) {
                    val item = viewModel.getAllProductsParticipant()
                    listProducts.forEach { itemProduct ->
                        item.forEach { itemProductParticipant ->
                            if (itemProduct.id == itemProductParticipant.productsId) {
                                listParticipant.forEach { itemParticipant ->
                                    if (itemProductParticipant.participantId == itemParticipant.id) {
                                        nameParticipant.add(itemParticipant.firstName + " " + itemParticipant.lastName)
                                    }
                                }
                            }
                        }
                    }
                }
                val typeListAdapter = listProducts.let {
                    ArchiveHikeProductsAdapter(it, nameParticipant)
                }
                binding.recyclerViewListProducts.adapter = typeListAdapter

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
}