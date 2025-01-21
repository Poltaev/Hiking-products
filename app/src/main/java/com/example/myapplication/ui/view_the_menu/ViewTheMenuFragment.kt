package com.example.myapplication.ui.view_the_menu

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
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.databinding.FragmentAddingAParticipantBinding

import com.example.myapplication.databinding.FragmentViewTheMenuBinding
import com.example.myapplication.ui.adapters.ThisHikeMenuAdapter
import com.example.myapplication.ui.adapters.ThisHikeProductsAdapter
import com.example.myapplication.ui.this_hike.ThisHikeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewTheMenuFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentViewTheMenuBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewTheMenuViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ViewTheMenuViewModel(hikeDao) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTheMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllMenuFlow().collect {
                val listMenu = it
                val typeListAdapter = listMenu.let {
                    ThisHikeMenuAdapter(it){onItemClick(it)}
                }
                binding.recyclerViewListMenu.adapter = typeListAdapter
            }
        }
    }
    override fun onPause() {
        super.onPause()
        job.cancel()
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
    private fun onItemClick(item: ThisHikeMealIntakeSheet) {
        val bundle = Bundle().apply {
            item.id.let { putInt("listTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_viewTheMenuFragment_to_watchingASingleMealFragment,
            bundle
        )
    }
}