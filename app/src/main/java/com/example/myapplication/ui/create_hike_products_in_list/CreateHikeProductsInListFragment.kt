package com.example.myapplication.ui.create_hike_products_in_list

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
import com.example.myapplication.databinding.FragmentCreateHikeListProductsBinding
import com.example.myapplication.databinding.FragmentCreateHikeParticipantBinding
import com.example.myapplication.databinding.FragmentCreateHikeProductsInListBinding
import com.example.myapplication.ui.create_hike_list_products.CreateHikeListProductsViewModel
import com.example.myapplication.ui.create_hike_participant.CreateHikeParticipantViewModel

class CreateHikeProductsInListFragment : Fragment() {

    private var _binding: FragmentCreateHikeProductsInListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeProductsInListViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeProductsInListViewModel(hikeDao) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateHikeProductsInListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}