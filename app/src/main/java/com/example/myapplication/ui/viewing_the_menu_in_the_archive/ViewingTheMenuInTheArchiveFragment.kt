package com.example.myapplication.ui.viewing_the_menu_in_the_archive

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class ViewingTheMenuInTheArchiveFragment : Fragment() {

    companion object {
        fun newInstance() = ViewingTheMenuInTheArchiveFragment()
    }

    private val viewModel: ViewingTheMenuInTheArchiveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_viewing_the_menu_in_the_archive, container, false)
    }
}