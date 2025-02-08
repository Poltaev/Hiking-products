package com.example.hike_menu_calculator.ui.hike_archive

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
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.databinding.FragmentHikeArchiveBinding
import com.example.hike_menu_calculator.ui.adapters.StorageArchiveAdapterAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HikeArchiveFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentHikeArchiveBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HikeArchiveViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return HikeArchiveViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHikeArchiveBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Main) {
            val archiveHike = async(Dispatchers.IO) { viewModel.getAllArchiveHike() }
            val listArchiveHike = archiveHike.await()
            listArchiveHike.let {
                val adapter = StorageArchiveAdapterAdapter(it) { onItemClick(it) }
                launch(Dispatchers.Main) {
                    binding.recyclerViewArchive.adapter = adapter
                }
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

    private fun onItemClick(item: ArchiveHike) {
        val bundle = Bundle().apply {
            item.id.let { putInt("archiveHike", it) }
        }
        findNavController().navigate(
            R.id.action_hiking_archive_to_viewingAHikeFragment, bundle
        )
    }
}