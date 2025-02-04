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
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeEquipmentBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeEquipmentAdapter
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
                                item.theSoleOwner,
                                item.nameOwner,
                                item.idOwner,
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
                                item.theSoleOwner,
                                item.nameOwner,
                                item.idOwner,
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