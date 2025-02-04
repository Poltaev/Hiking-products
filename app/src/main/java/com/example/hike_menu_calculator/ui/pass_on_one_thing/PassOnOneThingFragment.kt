package com.example.hike_menu_calculator.ui.pass_on_one_thing

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.databinding.FragmentPassOnOneThingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PassOnOneThingFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentPassOnOneThingBinding? = null
    private var productId = 9999
    private var equipmentId = 9999
    private val binding get() = _binding!!

    private val viewModel: PassOnOneThingViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return PassOnOneThingViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getInt("productTypeId")
            equipmentId = it.getInt("equipmentTypeId")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPassOnOneThingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        job = lifecycleScope.launch(Dispatchers.IO) {
            if (productId != 0) {
                viewModel.getListFood().forEach {
                    if (it.id == productId) {
                        launch(Dispatchers.Default) {
                            binding.textViewNameEquipment.text = it.name
                            binding.imageViewPhoto.setImageResource(R.drawable.hamburger)
                            binding.textViewWeight.text = "Вес продукта: ${it.remainingWeight} г."
                        }
                        val listIdParticipant = mutableListOf<Int>()
                        viewModel.getProductsParticipants().forEach {
                            if (it.productsId == productId) {
                                listIdParticipant.add(it.participantId)
                            }
                        }
                        val listParticipant = viewModel.getAllListParticipant()
                        listParticipant.forEach {
                            if (it.id == listIdParticipant[0]) {
                                binding.textViewNameParticipants.text =
                                    "Несет: ${it.firstName + " " + it.lastName}"
                            }
                        }
                    }
                }
            }
            if (equipmentId != 0) {
                viewModel.getAllEquipment().forEach {
                    if (it.id == equipmentId) {
                        launch(Dispatchers.Default) {
                            binding.textViewNameEquipment.text = it.name
                            binding.imageViewPhoto.setImageResource(R.drawable.tent)
                            binding.textViewWeight.text = "Вес снаряжения: ${it.weight} г."
                            val listParticipant = viewModel.getAllListParticipant()
                            val idParticipant = it.participantsId
                            listParticipant.forEach {
                                if (it.id == idParticipant) binding.textViewNameParticipants.text =
                                    "Несет: ${it.firstName + " " + it.lastName}"
                            }
                        }
                    }
                }
            }
            val listParticipant = mutableListOf<String>()
            viewModel.getAllListParticipant().forEach {
                listParticipant.add(it.firstName + " " + it.lastName)
            }
            launch(Dispatchers.Default) {
                val adapter = ArrayAdapter(
                    requireContext().applicationContext,
                    android.R.layout.simple_spinner_item, listParticipant
                )
                binding.spinnerlistParticipant.adapter = adapter
            }
            binding.buttonTransferTheEquipment.setOnClickListener {
                if (productId != 0) {
                    val partisipantSpinner = binding.spinnerlistParticipant.selectedItem.toString()
                    viewModel.transferTheProduct(productId, partisipantSpinner, listParticipant)
                    val toast = Toast.makeText(
                        requireContext().applicationContext,
                        "Продукт передан",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                if (equipmentId != 0) {
                    val partisipantSpinner = binding.spinnerlistParticipant.selectedItem.toString()
                    viewModel.transferTheEquiopment(
                        equipmentId,
                        partisipantSpinner,
                        listParticipant
                    )
                    val toast = Toast.makeText(
                        requireContext().applicationContext,
                        "Снаряжение передано",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
                binding.buttonTransferTheEquipment.isEnabled = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}