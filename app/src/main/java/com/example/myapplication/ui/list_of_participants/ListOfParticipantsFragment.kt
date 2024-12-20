package com.example.myapplication.ui.list_of_participants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.databinding.FragmentListOfParticipantsBinding
import com.example.myapplication.ui.adapters.ListParticipantsAdapter
import com.example.myapplication.ui.hike_archive.HikeArchiveViewModel
import com.example.myapplication.ui.this_hike.ThisHikeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ListOfParticipantsFragment : Fragment() {

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
        lifecycleScope.launch(Dispatchers.IO) {
            val getListParticipants = viewModel.getAllParticipantsList()
            if (getListParticipants.size == 0) {

                viewModel.addParticipants(
                    1,
                    "Photo",
                    "Для добавления",
                    "участника",
                    "нажмите сюда, или на кнопку",
                    "17",
                    10.0,
                    10.0,
                    false

                )
                val getParticipantsList = viewModel.getAllParticipantsList()
                val ParticipantsAdapter =
                    getParticipantsList.let { ListParticipantsAdapter(it) { onItemClick(it) } }
                binding.recyclerViewParticipants.adapter = ParticipantsAdapter

            } else {
                val getParticipantsList = viewModel.getAllParticipantsList()
                val ParticipantsAdapterList =
                    getParticipantsList.let { ListParticipantsAdapter(it) { onItemClick(it) } }
                binding.recyclerViewParticipants.adapter = ParticipantsAdapterList
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