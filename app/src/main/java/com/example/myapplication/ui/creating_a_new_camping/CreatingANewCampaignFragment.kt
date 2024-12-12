package com.example.myapplication.ui.creating_a_new_camping

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class CreatingANewCampaignFragment : Fragment() {

    companion object {
        fun newInstance() = CreatingANewCampaignFragment()
    }

    private val viewModel: CreatingANewCampaignViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_creating_a_new_campaign, container, false)
    }
}