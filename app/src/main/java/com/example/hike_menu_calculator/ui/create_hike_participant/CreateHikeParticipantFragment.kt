package com.example.hike_menu_calculator.ui.create_hike_participant

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
import androidx.recyclerview.widget.DiffUtil
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeParticipantBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeParticipantsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateHikeParticipantFragment : Fragment() {

    lateinit var job: Job

    private var _binding: FragmentCreateHikeParticipantBinding? = null

    private var adapter: CreateHikeParticipantsAdapter? = null

    private var listParticipant = listOf<Participants>()

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
        job = lifecycleScope.launch(Dispatchers.Main) {
            val listParticipants = async(Dispatchers.IO) { viewModel.getAllParticipantsList() }
            listParticipant = listParticipants.await()
            adapter = CreateHikeParticipantsAdapter(listParticipant) { onItemClick(it) }
            binding.recyclerViewListParticipant.adapter = adapter
        }

        binding.buttonFurther.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
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
                            it.weightWithLoad,
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


    private fun onItemClick(item: Participants) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listParticipants = viewModel.getAllParticipantsList()
            runBlocking {
                listParticipants.forEach {
                    if (item.id == it.id) {
                        if (it.participationInTheCampaign) {
                            viewModel.upDateParticipant(
                                item.id,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                item.weightWithLoad,
                                false
                            )

                        } else {
                            viewModel.upDateParticipant(
                                item.id,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                item.weightWithLoad,
                                true
                            )
                        }
                    }
                }
            }
            val newListParticipant = viewModel.getAllParticipantsList()
            launch(Dispatchers.Main) {
                upDateList(newListParticipant)

            }
        }
    }

    private fun upDateList(newElement: List<Participants>) {
        val result = DiffUtil.calculateDiff(
            CreateParticipantDiffUtilCallback(
                oldElement = listParticipant,
                newElement = newElement
            )
        )
        adapter?.let { adapter ->
            adapter.data = newElement
            result.dispatchUpdatesTo(adapter)
        }
        listParticipant = newElement
    }
}