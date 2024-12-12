package com.example.myapplication.ui.viewing_a_hike

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class ViewingAHikeFragment : Fragment() {

    companion object {
        fun newInstance() = ViewingAHikeFragment()
    }

    private val viewModel: ViewingAHikeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_viewing_a_hike, container, false)
    }
}