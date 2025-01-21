package com.example.myapplication.ui.pass_on_one_thing

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentPassOnOneThingBinding
import com.example.myapplication.databinding.FragmentViewingABackpackBinding
import com.example.myapplication.ui.adapters.ThisHikeMenuAdapter
import com.example.myapplication.ui.viewing_a_backpack.ViewingABackpackViewModel
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
        super.onViewCreated(view, savedInstanceState)
        if (productId != 9999) {
            job = lifecycleScope.launch {
                viewModel.getAllListFoodFlow().collect {
                    it.forEach {
                        if (it.id == productId) {
                            binding.textViewNameEquipment.text = it.name
                            binding.imageViewPhoto.setImageResource(R.drawable.hamburger)
                            binding.textViewWeight.text = "Вес продукта: ${it.remainingWeight} г."
                            val listParticipantsProduct = viewModel.getAllListParticipantProduct()
                            val listIdParticipant = mutableListOf<Int>()
                            listParticipantsProduct.forEach {
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

            }
        }
        if (equipmentId != 9999) {
            job = lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getAllEquipmentFlow().collect {
                    it.forEach {
                        if (it.id == equipmentId) {
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
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val listParticipant = mutableListOf<String>()
            viewModel.getAllListParticipant().forEach {
                listParticipant.add(it.firstName + " " + it.lastName)
            }
            val adapter = ArrayAdapter(
                requireContext().applicationContext,
                android.R.layout.simple_spinner_item, listParticipant
            )
            binding.spinnerlistParticipant.adapter = adapter
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