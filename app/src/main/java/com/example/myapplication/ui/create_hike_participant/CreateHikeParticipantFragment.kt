package com.example.myapplication.ui.create_hike_participant

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
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.FragmentCreateHikeParticipantBinding
import com.example.myapplication.ui.adapters.CreateHikeParticipantsAdapter
import com.example.myapplication.ui.adapters.ListAddProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeParticipantFragment : Fragment() {

    lateinit var job: Job

    private var _binding: FragmentCreateHikeParticipantBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeParticipantViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeParticipantViewModel(hikeDao) as T
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateHikeParticipantBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkAndUpDateTheList()
        binding.buttonFurther.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listParticipant = viewModel.getAllParticipantsList()
                listParticipant.forEach {
                    if (it.participationInTheCampaign) {
                        viewModel.createHikeParticipant(
                            it.id,
                            1,
                            it.photo,
                            it.firstName,
                            it.lastName,
                            it.gender,
                            it.age,
                            it.maximumPortableWeight,
                            it.weightOfPersonalItems,
                            ""
                        )
                    }
                }
            }
            findNavController().navigate(
                R.id.action_createHikeParticipantFragment_to_createHikeEquipmentFragment
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
            viewModel.getAllParticipantFlow().collect {
                delay(100)
                val getParticipantList = it
                val ParticipantsAdapter =
                    getParticipantList.let {
                        CreateHikeParticipantsAdapter(
                            it
                        ) { onItemClick(it) }
                    }
                binding.recyclerViewListParticipant.adapter = ParticipantsAdapter
            }
        }
    }

    private fun onItemClick(item: Participants) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listParticipant = viewModel.getAllParticipantsList()
            listParticipant.forEach {
                if (item.id == it.id) {
                    if (it.participationInTheCampaign) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateParticipant(
                                item.id,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                false
                            )
                        }
                    } else {
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.upDateParticipant(
                                item.id,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                true
                            )
                        }
                    }
                }
            }
            job.cancel()
            checkAndUpDateTheList()
        }
    }
}