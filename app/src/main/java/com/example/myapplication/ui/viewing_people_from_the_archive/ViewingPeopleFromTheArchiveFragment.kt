package com.example.myapplication.ui.viewing_people_from_the_archive

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
import com.example.myapplication.databinding.FragmentViewingPeopleBinding
import com.example.myapplication.databinding.FragmentViewingPeopleFromTheArchiveBinding
import com.example.myapplication.ui.this_hike.ThisHikeViewModel

class ViewingPeopleFromTheArchiveFragment : Fragment() {

    private var _binding: FragmentViewingPeopleFromTheArchiveBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewingPeopleFromTheArchiveViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ViewingPeopleFromTheArchiveViewModel(hikeDao) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewingPeopleFromTheArchiveBinding.inflate(inflater, container, false)
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