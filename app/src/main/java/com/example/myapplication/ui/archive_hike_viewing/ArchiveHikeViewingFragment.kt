package com.example.myapplication.ui.archive_hike_viewing

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
import com.example.myapplication.databinding.FragmentViewingAHikeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArchiveHikeViewingFragment : Fragment() {
    lateinit var job: Job
    private var id = 0
    private var _binding: FragmentViewingAHikeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArchiveHikeViewingModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveHikeViewingModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("archiveHike")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentViewingAHikeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Default) {

            val archiveHike = async(Dispatchers.IO) { viewModel.getAllArchiveHike() }
            archiveHike.await().forEach { archiveHike ->
                if (archiveHike.id == id) {
                    binding.textViewNameHike.text = archiveHike.name
                }
            }
            binding.buttonMenu.setOnClickListener {
                val bundle = Bundle().apply {
                    id.let { putInt("archiveHike", id) }
                }
                findNavController().navigate(
                    R.id.action_viewingAHikeFragment_to_viewingPeopleFromTheArchiveFragment, bundle
                )
            }
            binding.buttonProducts.setOnClickListener {
                val bundle = Bundle().apply {
                    id.let { putInt("archiveHike", id) }
                }
                findNavController().navigate(
                    R.id.action_archiveHikeViewingFragment_to_archiveHikeProductsFragment, bundle
                )
            }
            binding.buttonBackpacks.setOnClickListener {
                val bundle = Bundle().apply {
                    id.let { putInt("archiveHike", id) }
                }
                findNavController().navigate(
                    R.id.action_viewingAHikeFragment_to_archiveHikeParticipantFragment, bundle
                )
            }
            binding.buttonEquipment.setOnClickListener {
                val bundle = Bundle().apply {
                    id.let { putInt("archiveHike", id) }
                }
                findNavController().navigate(
                    R.id.action_archiveHikeViewingFragment_to_archiveHikeEquipmentFragment, bundle
                )
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
}