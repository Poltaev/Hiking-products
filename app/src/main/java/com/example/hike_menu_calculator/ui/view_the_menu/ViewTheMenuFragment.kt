package com.example.hike_menu_calculator.ui.view_the_menu

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
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet

import com.example.hike_menu_calculator.databinding.FragmentViewTheMenuBinding
import com.example.hike_menu_calculator.ui.adapters.ThisHikeMenuAdapter
import kotlinx.coroutines.Dispatchers
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
                launch(Dispatchers.Main) {
                    binding.recyclerViewListMenu.adapter = typeListAdapter
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