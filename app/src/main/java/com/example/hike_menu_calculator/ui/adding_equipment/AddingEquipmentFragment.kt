package com.example.hike_menu_calculator.ui.adding_equipment

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
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.databinding.FragmentAddingEquipmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddingEquipmentFragment : Fragment() {
    private var id = 1
    private var name = "Name"
    private var photo = "Photo"
    private var weight = 1000
    private var theVolumeItem = false
    private var theSoleOwner = false
    private var nameOwner = "NameAll"
    private var idOwner = 0
    private var equipmentInTheCampaign = true

    private var _binding: FragmentAddingEquipmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AddingEquipmentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddingEquipmentViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingEquipmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("equipmentId")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewPhotoEquipment.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext().applicationContext,
                R.drawable.tent
            )
        )
        if (id != 9999) {
            lifecycleScope.launch(Dispatchers.IO) {
                val listEquipment = viewModel.getAllEquipmentList()
                listEquipment.forEach {
                    if (it.id == id) {
                        id = it.id
                        name = it.name
                        weight = it.weight
                    }
                }
            }
            lifecycleScope.launch(Dispatchers.Main) {
                delay(100)
                binding.textInputLayoutName.editText?.setText(name)
                binding.textInputLayoutWeight.editText?.setText(weight.toString())
            }
        }
        binding.enterName.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                name
            } else {
                name = text.toString()
            }
        }
        binding.enterWeight.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                weight
            } else {
                weight = text.toString().toInt()
            }
        }

        binding.buttonAddEquipment.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listEquipment = viewModel.getAllEquipmentList()
                val id = listEquipment[listEquipment.size - 1].id + 1
                viewModel.addEquipment(
                    id,
                    name,
                    photo,
                    weight,
                    theVolumeItem,
                    theSoleOwner,
                    nameOwner,
                    idOwner,
                    equipmentInTheCampaign
                )
            }
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Снаряжение добавлено, можно добавлять следующее",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        binding.buttonDeleteEquipment.setOnClickListener {
            if (id != 9999) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val listEquipment = viewModel.getAllEquipmentList()
                    listEquipment.forEach {
                        if (it.id == id) {
                            viewModel.deleteEquipment(it)
                        }
                    }
                }

                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Снаряжение удалено",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Снаряжение для удаления не выбрано",
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