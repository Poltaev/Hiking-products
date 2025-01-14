package com.example.myapplication.ui.view_the_menu

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentAddingAParticipantBinding

import com.example.myapplication.databinding.FragmentViewTheMenuBinding
import com.example.myapplication.ui.this_hike.ThisHikeViewModel

class ViewTheMenuFragment : Fragment() {

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}