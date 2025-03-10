package com.example.hike_menu_calculator.ui.archive_hike_menu

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
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.databinding.FragmentArchiveHikeMenuBinding
import com.example.hike_menu_calculator.ui.adapters.ArchiveHikeMenuAdapter
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArchiveHikeMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch(Dispatchers.Main) {
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