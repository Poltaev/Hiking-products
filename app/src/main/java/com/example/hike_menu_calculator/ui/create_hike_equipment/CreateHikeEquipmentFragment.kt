package com.example.hike_menu_calculator.ui.create_hike_equipment

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
import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeEquipmentBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeEquipmentAdapter
import com.example.hike_menu_calculator.ui.adapters.CreateHikeParticipantsAdapter
import com.example.hike_menu_calculator.ui.create_hike_participant.CreateParticipantDiffUtilCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeEquipmentFragment : Fragment() {

    lateinit var job: Job

    private var _binding: FragmentCreateHikeEquipmentBinding? = null

    private val binding get() = _binding!!

    private var adapter: CreateHikeEquipmentAdapter? = null

    private var listEquipment = listOf<Equipment>()

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
        job = lifecycleScope.launch(Dispatchers.Main) {
            val listEquipments = async(Dispatchers.IO) { viewModel.getAllEquipmentList() }
            listEquipment = listEquipments.await()
            adapter = CreateHikeEquipmentAdapter(listEquipment) { onItemClick(it) }
            binding.recyclerViewListEquipment.adapter = adapter
        }
        binding.buttonFurther.setOnClickListener {
            viewModel.addEquipmentToBackpack()
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

    private fun onItemClick(item: Equipment) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listEquipments = viewModel.getAllEquipmentList()
            listEquipments.forEach {
                if (item.id == it.id) {
                    if (it.equipmentInTheCampaign) {
                        viewModel.upDateEquipment(
                            item.id,
                            item.name,
                            item.photo,
                            item.weight,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            false
                        )
                    } else {
                        viewModel.upDateEquipment(
                            item.id,
                            item.name,
                            item.photo,
                            item.weight,
                            item.theVolumeItem,
                            item.theSoleOwner,
                            item.nameOwner,
                            item.idOwner,
                            true
                        )
                    }
                }
            }
            val newListEquipment = viewModel.getAllEquipmentList()
            launch(Dispatchers.Main) {
                upDateList(newListEquipment)
            }
        }
    }

    private fun upDateList(newElement: List<Equipment>) {
        val result = DiffUtil.calculateDiff(
            CreateEquipmentDiffUtilCallback(
                oldElement = listEquipment,
                newElement = newElement
            )
        )
        adapter?.let { adapter ->
            adapter.data = newElement
            result.dispatchUpdatesTo(adapter)
        }
        listEquipment = newElement
    }
}