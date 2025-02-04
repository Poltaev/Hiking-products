package com.example.hike_menu_calculator.ui.this_hike_list_of_participants

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
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.databinding.FragmentThisHikeListOfParticipantsBinding
import com.example.hike_menu_calculator.ui.adapters.ThisHikeParticipantAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ThisHikeListOfParticipantsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeListOfParticipantsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeListOfParticipantsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeListOfParticipantsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThisHikeListOfParticipantsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Main) {
            runBlocking(Dispatchers.IO) {
                viewModel.upDateParticipant()
            }
            val listParticipant = async(Dispatchers.IO) { viewModel.getAllParticipants() }
            val typeListAdapter = listParticipant.await().let {
                ThisHikeParticipantAdapter(it) { onItemClick(it) }
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

    private fun onItemClick(item: ThisHikeParticipants) {
        val bundle = Bundle().apply {
            item.id.let { putInt("participantId", it) }
        }
        findNavController().navigate(
            R.id.action_thisHikeListOfParticipantsFragment_to_viewingABackpackFragment,
            bundle
        )
    }
}