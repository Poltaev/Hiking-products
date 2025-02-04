package com.example.myapplication.ui.archive_hike_participant

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
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.databinding.FragmentViewingPeopleFromTheArchiveBinding
import com.example.myapplication.ui.adapters.ArchiveHikeParticipantAdapter
import com.example.myapplication.ui.adapters.ThisHikeParticipantAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArchiveHikeParticipantFragment : Fragment() {
    lateinit var job: Job
    private var id = 0
    private var _binding: FragmentViewingPeopleFromTheArchiveBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ArchiveHikeParticipantViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveHikeParticipantViewModel(hikeDao) as T
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
        _binding = FragmentViewingPeopleFromTheArchiveBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Main) {
            runBlocking(Dispatchers.IO) {
                viewModel.upDateParticipant()
            }
            val listParticipantForAdapter = mutableListOf<ArchiveParticipants>()
            val listParticipant = async(Dispatchers.IO) { viewModel.getAllParticipants() }
            listParticipant.await().forEach {
                if (it.hikeId == id){
                    listParticipantForAdapter.add(it)
                }
            }
            val typeListAdapter = listParticipantForAdapter.let {
                ArchiveHikeParticipantAdapter(it) { onItemClick(it) }
            }
            binding.recyclerViewListEquipmentParticipants.adapter = typeListAdapter
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

    private fun onItemClick(item: ArchiveParticipants) {
        val bundle = Bundle().apply {
            item.id.let { putInt("participantId", it) }
        }
        findNavController().navigate(
            R.id.action_archiveHikeParticipantFragment_to_archiveViewingABackpackFragment,
            bundle
        )
    }
}