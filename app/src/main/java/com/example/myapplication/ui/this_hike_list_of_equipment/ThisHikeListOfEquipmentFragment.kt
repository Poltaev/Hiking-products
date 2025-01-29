package com.example.myapplication.ui.this_hike_list_of_equipment

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
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.databinding.FragmentThisHikeListOfEquipmentBinding
import com.example.myapplication.databinding.FragmentThisHikeListOfProductsBinding
import com.example.myapplication.ui.adapters.ListTypeProductsAdapter
import com.example.myapplication.ui.adapters.ThisHikeEquipmentAdapter
import com.example.myapplication.ui.this_hike_list_of_products.ThisHikeListOfProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ThisHikeListOfEquipmentFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeListOfEquipmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeListOfEquipmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeListOfEquipmentViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThisHikeListOfEquipmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllThisHikeListEquipmentFlow().collect {
                val listEquipment = it
                val nameParticipant = mutableListOf<String>()
                runBlocking(Dispatchers.IO) {
                    val listParticipant = viewModel.getAllThisHikeListParticipant()
                    listEquipment.forEach {
                        listParticipant.forEach { item ->
                            if (it.participantsId == item.id) {
                                nameParticipant.add(item.firstName + " " + item.lastName)
                            }
                        }
                    }
                }
                val typeListAdapter = listEquipment.let {
                    ThisHikeEquipmentAdapter(it, nameParticipant) { onItemClick(it) }
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

    private fun onItemClick(item: ThisHikeEquipment) {
        val bundle = Bundle().apply {
            item.id.let { putInt("equipmentTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_thisHikeListOfEquipmentFragment_to_passOnOneThingFragment,
            bundle
        )
    }
}