package com.example.myapplication.ui.archive_hike_menu

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
import com.example.myapplication.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.databinding.FragmentArchiveHikeMenuBinding
import com.example.myapplication.ui.adapters.ArchiveHikeMenuAdapter
import com.example.myapplication.ui.adapters.ThisHikeMenuAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArchiveHikeMenuFragment : Fragment() {
    lateinit var job: Job
    private var id = 0
    private var _binding: FragmentArchiveHikeMenuBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ArchiveHikeMenuViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ArchiveHikeMenuViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("archiveHike")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            val chackListMenu = mutableListOf<ArchiveHikeMealIntakeSheet>()
            val listMenu = async(Dispatchers.IO) { viewModel.getAllMenuFlow() }
            listMenu.await().forEach {
                if (id == it.hikeId) {
                    chackListMenu.add(it)
                }
            }
            val typeListAdapter = chackListMenu.let {
                ArchiveHikeMenuAdapter(it) { onItemClick(it) }
            }
            binding.recyclerViewListMenu.adapter = typeListAdapter

        }
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun onItemClick(item: ArchiveHikeMealIntakeSheet) {
        val bundle = Bundle().apply {
            item.id.let { putInt("listTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_archiveHikeMenuFragment_to_archiveViewingASingleMealFragment,
            bundle
        )
    }
}