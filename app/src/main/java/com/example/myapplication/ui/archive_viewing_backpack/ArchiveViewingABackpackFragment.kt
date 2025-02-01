package com.example.myapplication.ui.archive_viewing_backpack

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentArchiveViewingABackpackBinding
import com.example.myapplication.databinding.FragmentViewingABackpackBinding
import com.example.myapplication.ui.adapters.ArchiveHikeEquipmentBackpackAdapter
import com.example.myapplication.ui.adapters.ArchiveHikeProductsBackpackAdapter
import com.example.myapplication.ui.adapters.ThisHikeEquipmentBackpackAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsBackpackAdapter
import com.example.myapplication.ui.viewing_a_backpack.ViewingABackpackViewModel

class ArchiveViewingABackpackFragment : Fragment() {
    private var _binding: FragmentArchiveViewingABackpackBinding? = null
    private var participantsId = 1
    private val binding get() = _binding!!

    private val viewModel: ArchiveViewingABackpackViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveViewingABackpackViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            participantsId = it.getInt("participantId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArchiveViewingABackpackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeListFoodAdapter = viewModel.getListFood(participantsId).let {
            ArchiveHikeProductsBackpackAdapter(it)
        }
        binding.recyclerViewListEating.adapter = typeListFoodAdapter


        val typeListEquipmentAdapter = viewModel.getListEquipment(participantsId).let {
            ArchiveHikeEquipmentBackpackAdapter(it)
        }
        binding.recyclerViewListEquipment.adapter = typeListEquipmentAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}