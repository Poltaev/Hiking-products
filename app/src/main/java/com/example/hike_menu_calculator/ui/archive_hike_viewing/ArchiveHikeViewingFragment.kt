package com.example.hike_menu_calculator.ui.archive_hike_viewing

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.databinding.FragmentViewingAHikeBinding
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
        job = lifecycleScope.launch(Dispatchers.Main) {

            val archiveHike = async(Dispatchers.IO) { viewModel.getAllArchiveHike() }
            archiveHike.await().forEach { archiveHike ->
                if (archiveHike.id == id) {
                    binding.textViewNameHike.text = archiveHike.name
                }
            }
            binding.buttonDeleteIt.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.deleteAcrhiveHike(id)
                }
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Поход из архива удален",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                binding.buttonDeleteIt.isEnabled = false
                binding.buttonMoveItThisHike.isEnabled = false
            }
            binding.buttonMoveItThisHike.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.moveItThisHike(id)
                }
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Поход передан в текущий, предыдущий удален",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                binding.buttonDeleteIt.isEnabled = false
                binding.buttonMoveItThisHike.isEnabled = false
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