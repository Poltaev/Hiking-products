package com.example.myapplication.ui.viewing_a_backpack

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
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.databinding.FragmentViewingABackpackBinding
import com.example.myapplication.databinding.FragmentWatchingASingleMealBinding
import com.example.myapplication.ui.adapters.ThisHikeEquipmentBackpackAdapter
import com.example.myapplication.ui.adapters.ThisHikeParticipantAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsBackpackAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsMenuAdapter
import com.example.myapplication.ui.watching_a_single_meal.WatchingASingleMealViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ViewingABackpackFragment : Fragment() {
    private var _binding: FragmentViewingABackpackBinding? = null
    private var participantsId = 1
    lateinit var job: Job
    private val binding get() = _binding!!

    private val viewModel: ViewingABackpackViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ViewingABackpackViewModel(hikeDao) as T
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
        _binding = FragmentViewingABackpackBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Main) {
            val listProduct = lifecycleScope.async(Dispatchers.IO) { viewModel.getListFood(participantsId) }
            val typeListFoodAdapter = listProduct.await().let {
                ThisHikeProductsBackpackAdapter(it) { onItemClickProduct(it) }
            }
            binding.recyclerViewListEating.adapter = typeListFoodAdapter

            val listEquipment = lifecycleScope.async(Dispatchers.IO) { viewModel.getListEquipment(participantsId) }
            val typeListEquipmentAdapter = listEquipment.await().let {
                ThisHikeEquipmentBackpackAdapter(it) { onItemClickEquipment(it) }
            }
            binding.recyclerViewListEquipment.adapter = typeListEquipmentAdapter
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

    private fun onItemClickProduct(item: ThisHikeProducts) {
        val bundle = Bundle().apply {
            item.id.let { putInt("productTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_viewingABackpackFragment_to_passOnOneThingFragment,
            bundle
        )
    }

    private fun onItemClickEquipment(item: ThisHikeEquipment) {
        val bundle = Bundle().apply {
            item.id.let { putInt("equipmentTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_viewingABackpackFragment_to_passOnOneThingFragment,
            bundle
        )
    }
}