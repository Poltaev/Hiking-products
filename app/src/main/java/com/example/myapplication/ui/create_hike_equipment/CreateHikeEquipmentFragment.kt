package com.example.myapplication.ui.create_hike_equipment

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
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.databinding.FragmentCreateHikeEquipmentBinding
import com.example.myapplication.databinding.FragmentCreateHikeParticipantBinding
import com.example.myapplication.ui.adapters.CreateHikeEquipmentAdapter
import com.example.myapplication.ui.adapters.CreateHikeParticipantsAdapter
import com.example.myapplication.ui.create_hike_participant.CreateHikeParticipantViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeEquipmentFragment : Fragment() {

    lateinit var job: Job

    private var _binding: FragmentCreateHikeEquipmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeEquipmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeEquipmentViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateHikeEquipmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndUpDateTheList()
        binding.buttonFurther.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listParticipantId = mutableListOf<Int>()
                viewModel.getAllParticipantList().forEach {
                    listParticipantId.add(it.id)
                }
                var x = 0
                viewModel.getAllEquipmentList().forEach {
                    if (it.equipmentInTheCampaign) {
                        if (x >= listParticipantId.size){ x = 0 }
                            viewModel.createHikeEquipment(
                                it.id,
                                listParticipantId[x],
                                it.name,
                                it.photo,
                                it.weight,
                                false,
                                false,
                                false,
                                ""
                            )
                        x ++
                    }
                }
            }
            findNavController().navigate(
                R.id.action_createHikeEquipmentFragment_to_createHikeProductsInListFragment
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
            viewModel.getAllEquipmentFlow().collect {
                val getEquipmentList = it
                val EquipmentAdapter =
                    getEquipmentList.let {
                        CreateHikeEquipmentAdapter(
                            it
                        ) { onItemClick(it) }
                    }
                binding.recyclerViewListEquipment.adapter = EquipmentAdapter
            }
        }
    }

    private fun onItemClick(item: Equipment) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listEquipment = viewModel.getAllEquipmentList()
            listEquipment.forEach {
                if (item.id == it.id) {
                    if (it.equipmentInTheCampaign) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateEquipment(
                                item.id,
                                item.name,
                                item.photo,
                                item.weight,
                                item.theVolumeItem,
                                false
                            )
                        }
                    } else {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateEquipment(
                                item.id,
                                item.name,
                                item.photo,
                                item.weight,
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