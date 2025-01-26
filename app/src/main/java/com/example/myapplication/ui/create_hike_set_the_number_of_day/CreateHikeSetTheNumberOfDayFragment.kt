package com.example.myapplication.ui.create_hike_set_the_number_of_day

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentCreateHikeParticipantBinding
import com.example.myapplication.databinding.FragmentCreateHikeSetTheNumberOfDayBinding
import com.example.myapplication.ui.create_hike_participant.CreateHikeParticipantViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateHikeSetTheNumberOfDayFragment : Fragment() {

    private var numberOfSnaks = 0
    private var numberOfDay = 1
    private var name = "Имя похода"
    private var _binding: FragmentCreateHikeSetTheNumberOfDayBinding? = null

    private val binding get() = _binding!!

    private val viewModel: CreateHikeSetTheNumberOfDayViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeSetTheNumberOfDayViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateHikeSetTheNumberOfDayBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.entertextInputName.doOnTextChanged { text, _, _, _ ->
            if (text == null) {
                name
            } else {
                name = text.toString()
            }
        }
        binding.buttonFurther.setOnClickListener {
            numberOfSnaks = binding.spinnerNamberOfSnacks.selectedItem.toString().toInt()
            numberOfDay = binding.spinnerNamberOfDay.selectedItem.toString().toInt()
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.deleteALL()
                viewModel.createHike(
                    name,
                    numberOfDay,
                    numberOfSnaks
                )

            }
            findNavController().navigate(
                R.id.action_createHikeSetTheNumberOfDayFragment_to_createHikeParticipantFragment
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}