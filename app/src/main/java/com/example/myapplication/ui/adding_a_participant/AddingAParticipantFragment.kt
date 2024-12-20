package com.example.myapplication.ui.adding_a_participant

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentAddingAParticipantBinding
import com.example.myapplication.databinding.FragmentThisHikeBinding
import com.example.myapplication.ui.this_hike.ThisHikeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddingAParticipantFragment : Fragment() {
    private var photo = "Photo"
    private var id = 1
    private var firstName = "Имя"
    private var secondName = "Фамилия"
    private var gender = "Пол"
    private var age = "28"
    private var limitWeight = 25.7
    private var weightOfPersonalItems = 10.3
    private var campaignParticipant = false

    private var _binding: FragmentAddingAParticipantBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AddingAParticipantViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddingAParticipantViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("participantsId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingAParticipantBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewPhotoParticipant.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext().applicationContext,
                R.drawable.man1
            )
        )
        binding.imageButtonBack.setOnClickListener {
            findNavController().navigate(
                R.id.action_addingAParticipantFragment_to_list_of_participants
            )
        }
        if (id != 9999) {
            lifecycleScope.launch(Dispatchers.IO) {
                val listParticipant = viewModel.getAllParticipantsList()
                listParticipant.forEach {
                    if (it.id == id) {
                        id = it.id
                        firstName = it.firstName
                        secondName = it.lastName
                        gender = it.gender
                        age = it.age
                        limitWeight = it.maximumPortableWeight
                        weightOfPersonalItems = it.weightOfPersonalItems
                    }
                }
            }
            lifecycleScope.launch(Dispatchers.Main) {
                delay(100)
                binding.textInputLayoutFirstName.editText?.setText(firstName)
                binding.textInputLayoutSecondName.editText?.setText(secondName)
                binding.textInputLayoutGender.editText?.setText(gender)
                binding.textInputLayoutAge.editText?.setText(age.toString())
                binding.textInputLimitWeight.editText?.setText(limitWeight.toString())
                binding.textInputWeightOfPersonalItems.editText?.setText(weightOfPersonalItems.toString())
            }
        }
        binding.enterFirstName.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                firstName = " "
            } else {
                firstName = text.toString()
            }
        }
        binding.enterSecondName.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                secondName = " "
            } else {
                secondName = text.toString()
            }
        }
        binding.enterGender.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                gender = " "
            } else {
                gender = text.toString()
            }
        }
        binding.enterAge.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                age = " "
            } else {
                age = text.toString()
            }
        }
        binding.enterLimitWeight.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                limitWeight = 0.0
            } else {
                limitWeight = text.toString().toDouble()
            }
        }
        binding.enterWeightOfPersonalItems.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                weightOfPersonalItems = 0.0
            } else {
                weightOfPersonalItems = text.toString().toDouble()
            }
        }
        binding.buttonAddParticipant.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listParticipant = viewModel.getAllParticipantsList()
                val id = listParticipant.size + 1
                viewModel.addParticipants(
                    id,
                    photo,
                    firstName,
                    secondName,
                    gender,
                    age,
                    limitWeight,
                    weightOfPersonalItems,
                    campaignParticipant
                )
            }
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Участник добавлен",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        binding.buttonDeleteParticipant.setOnClickListener {
            if (id != 9999) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val listParticipant = viewModel.getAllParticipantsList()
                    listParticipant.forEach {
                        if (it.id == id) {
                            viewModel.deleteParticipants(it)
                        }
                    }
                }

                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Участник удален",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Участник для удаления не выбран",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}