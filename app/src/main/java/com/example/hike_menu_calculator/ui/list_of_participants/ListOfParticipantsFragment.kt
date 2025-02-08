package com.example.hike_menu_calculator.ui.list_of_participants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.databinding.FragmentListOfParticipantsBinding
import com.example.hike_menu_calculator.ui.adapters.ListParticipantsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ListOfParticipantsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentListOfParticipantsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ListOfParticipantsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ListOfParticipantsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfParticipantsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllParticipantsFlow().collect {
                val getParticipantsList = it
                val ParticipantsAdapter =
                    getParticipantsList.let { ListParticipantsAdapter(it) { onItemClick(it) } }
                launch(Dispatchers.Main) {
                    binding.recyclerViewParticipants.adapter = ParticipantsAdapter
                }
            }
        }
        binding.buttonAddParticipants.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("participantsId", 9999)
            }
            findNavController().navigate(
                R.id.action_list_of_participants_to_addingAParticipantFragment,
                bundle
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

    private fun onItemClick(item: Participants) {
        val bundle = Bundle().apply {
            item.id.let { putInt("participantsId", it) }
        }
        findNavController().navigate(
            R.id.action_list_of_participants_to_addingAParticipantFragment,
            bundle
        )
    }
}