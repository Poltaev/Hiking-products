package com.example.myapplication.ui.list_of_equipment

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
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.databinding.FragmentEquipmentListBinding
import com.example.myapplication.ui.adapters.ListEquipmentAdapter
import com.example.myapplication.ui.adapters.ListParticipantsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EquipmentListFragment : Fragment() {

    private var _binding: FragmentEquipmentListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: EquipmentListViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return EquipmentListViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEquipmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val getListEquipment = viewModel.getAllEquipmentList()
            if (getListEquipment.size == 0) {

                viewModel.addEquipment(
                    1,
                    "Добавьте своё первое снаряжение, нажав на кнопку снизу, или на эту плитку снаряжения",
                    "Photo",
                    10.0,
                    false

                )
                val getEquipmentList = viewModel.getAllEquipmentList()
                val EquipmentAdapter =
                    getEquipmentList.let { ListEquipmentAdapter(it) { onItemClick(it) } }
                binding.recyclerViewEquipment.adapter = EquipmentAdapter

            } else {
                val getParticipantsList = viewModel.getAllEquipmentList()
                val ParticipantsAdapterList =
                    getParticipantsList.let { ListEquipmentAdapter(it) { onItemClick(it) } }
                binding.recyclerViewEquipment.adapter = ParticipantsAdapterList
            }
        }
        binding.buttonAddEquipment.setOnClickListener {
            val bundle = Bundle().apply {
               putInt("equipmentId", 9999)
            }
            findNavController().navigate(
                R.id.action_equipment_list_to_addingEquipmentFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
    private fun onItemClick(item: Equipment) {
        val bundle = Bundle().apply {
            item.id.let { putInt("equipmentId", it) }
        }
        findNavController().navigate(
            R.id.action_equipment_list_to_addingEquipmentFragment,
            bundle
        )
    }
}