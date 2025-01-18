package com.example.myapplication.ui.create_hike_list_products

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentCreateHikeListProductsBinding
import com.example.myapplication.databinding.FragmentCreateHikeParticipantBinding
import com.example.myapplication.ui.create_hike_participant.CreateHikeParticipantViewModel

class CreateHikeListProductsFragment : Fragment() {

    private var _binding: FragmentCreateHikeListProductsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeListProductsViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeListProductsViewModel(hikeDao) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateHikeListProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFurther.setOnClickListener {
            findNavController().navigate(
                R.id.action_createHikeListProductsFragment_to_this_hike
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}