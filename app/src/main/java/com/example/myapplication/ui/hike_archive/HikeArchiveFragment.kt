package com.example.myapplication.ui.hike_archive

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
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.databinding.FragmentHikeArchiveBinding
import com.example.myapplication.ui.adapters.StorageArchiveAdapterAdapter
import com.example.myapplication.ui.this_hike.ThisHikeViewModel
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
        job = lifecycleScope.launch(Dispatchers.Default) {
            val archiveHike = async(Dispatchers.IO) { viewModel.getAllArchiveHike() }
            archiveHike.await().let {
                StorageArchiveAdapterAdapter(it) { onItemClick(it) }
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