package com.example.myapplication.ui.archive_hike_equipment

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
import com.example.myapplication.dataBase.archive.ArchiveEquipment
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.databinding.FragmentArchiveHikeEquipmentBinding
import com.example.myapplication.databinding.FragmentViewingAHikeBinding
import com.example.myapplication.ui.adapters.ArchiveHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.StorageArchiveAdapterAdapter
import com.example.myapplication.ui.adapters.ThisHikeEquipmentAdapter
import com.example.myapplication.ui.archive_hike_viewing.ArchiveHikeViewingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArchiveHikeEquipmentFragment : Fragment() {
    lateinit var job: Job
    private var id = 0
    private var _binding: FragmentArchiveHikeEquipmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ArchiveHikeEquipmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveHikeEquipmentViewModel(hikeDao) as T
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
        _binding = FragmentArchiveHikeEquipmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllArchiveHikeListEquipmentFlow().collect {
                val listEquipment = mutableListOf<ArchiveEquipment>()
                val nameParticipant = mutableListOf<String>()
                val listParticipant = mutableListOf<ArchiveParticipants>()
                runBlocking(Dispatchers.IO) {
                    viewModel.getAllArchiveHikeListParticipant().forEach {
                        if (it.hikeId == id) {
                            listParticipant.add(it)
                        }
                    }
                }
                runBlocking {
                    listParticipant.forEach { participant ->
                        it.forEach { archiveEquipment ->
                            if (participant.id == archiveEquipment.participantId) {
                                listEquipment.add(archiveEquipment)
                            }
                        }

                    }
                }
                runBlocking(Dispatchers.IO) {
                    listEquipment.forEach {
                        listParticipant.forEach { item ->
                            if (it.participantId == item.id) {
                                nameParticipant.add(item.firstName + " " + item.lastName)
                            }
                        }
                    }
                }
                val typeListAdapter = listEquipment.let {
                    ArchiveHikeEquipmentAdapter(it, nameParticipant)
                }
                binding.recyclerViewListEquipment.adapter = typeListAdapter

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