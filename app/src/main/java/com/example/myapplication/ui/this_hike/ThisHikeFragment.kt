package com.example.myapplication.ui.this_hike

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
import com.example.myapplication.databinding.FragmentThisHikeBinding
import com.example.myapplication.ui.hike_archive.HikeArchiveViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ThisHikeFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThisHikeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkingForStorageAvailability()
        viewModel.сheckingForTypeLists()
        viewModel.checkingTheAvailabilityOfProducts()
        viewModel.checkParticipants()
        viewModel.checkEquipment()
        job = lifecycleScope.launch(Dispatchers.Main) {
            val thisHike = async(Dispatchers.IO) { viewModel.getAllThisHike() }
            if (thisHike.await().size != 0) {
                    binding.textViewNameHike.text = thisHike.await()[0].name
            }
        }
        binding.buttonCreateNewButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_this_hike_to_createHikeSetTheNumberOfDayFragment
            )
        }
        binding.buttonEquipment.setOnClickListener {
            findNavController().navigate(
                R.id.action_this_hike_to_thisHikeListOfEquipmentFragment
            )
        }
        binding.buttonProducts.setOnClickListener {
            findNavController().navigate(
                R.id.action_this_hike_to_listOfPurchasedProductsFragment
            )
        }
        binding.buttonBackpacks.setOnClickListener {
            findNavController().navigate(
                R.id.action_this_hike_to_viewingPeopleFragment
            )
        }
        binding.buttonMenu.setOnClickListener {
            findNavController().navigate(
                R.id.action_this_hike_to_viewTheMenuFragment
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
}