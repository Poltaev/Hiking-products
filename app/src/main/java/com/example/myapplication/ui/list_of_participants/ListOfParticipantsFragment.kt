package com.example.myapplication.ui.list_of_participants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentListOfParticipantsBinding
import com.example.myapplication.ui.hike_archive.HikeArchiveViewModel


class ListOfParticipantsFragment : Fragment() {

    private var _binding: FragmentListOfParticipantsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ListOfParticipantsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfParticipantsBinding.inflate(inflater, container, false)
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