package com.example.myapplication.ui.viewing_the_product

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentAddingAParticipantBinding
import com.example.myapplication.databinding.FragmentViewingTheProductBinding
import com.example.myapplication.ui.this_hike.ThisHikeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewingTheProductFragment : Fragment() {

    private var id = 1
    private var name = "Имя"
    private var weightForPerson = 1
    private var packageWeight = 1
    private var theVolumeItem = false
    private var weWillUseItInTheCurrentCampaign = false

    private var _binding: FragmentViewingTheProductBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ViewingTheProductViewModel by viewModels{
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ViewingTheProductViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("productsId")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewingTheProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (id != 9999) {
            lifecycleScope.launch(Dispatchers.IO) {
                val listProducts = viewModel.getAllProductsList()
                listProducts.forEach {
                    if (it.id == id) {
                        id = it.id
                        name = it.name
                        weightForPerson = it.weightForPerson
                        packageWeight = it.packageWeight
                        weWillUseItInTheCurrentCampaign = it.weWillUseItInTheCurrentCampaign
                    }
                }
            }
            lifecycleScope.launch(Dispatchers.Main) {
                delay(100)
                binding.textInputName.editText?.setText(name)
                binding.textInputWeightOfOneServing.editText?.setText(weightForPerson.toString())
                binding.textInputWeightPackage.editText?.setText(packageWeight.toString())
            }
        }
        binding.buttonAddInTypeOfMeal.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("productsId", id)
            }
            findNavController().navigate(
                R.id.action_viewingTheProductFragment_to_addThisProductInSomeListProductFragment,
                bundle
            )
        }
        binding.entertextInputName.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                name = " "
            } else {
                name = text.toString()
            }
        }
        binding.entertextInputWeightOfOneServing.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                weightForPerson = 0
            } else {
                weightForPerson = text.toString().toInt()
            }
        }
        binding.entertextInputWeightPackage.doOnTextChanged { text, _, _, _ ->
            if (text == null){
                packageWeight = 0
            } else {
                packageWeight = text.toString().toInt()
            }
        }
        binding.buttonSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val listProducts = viewModel.getAllProductsList()
                val id = listProducts.size + 1
                viewModel.addProducts(
                    id,
                    name,
                    weightForPerson,
                    packageWeight,
                    theVolumeItem,
                    weWillUseItInTheCurrentCampaign
                )
            }
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Продукт добавлен",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        binding.buttonDelete.setOnClickListener {
            if (id != 9999) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val listProducts = viewModel.getAllProductsList()
                    listProducts.forEach {
                        if (it.id == id) {
                            viewModel.deleteProducts(it)
                        }
                    }
                }

                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Продукт удален",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    requireContext().applicationContext,
                    "Продукт для удаления не выбран",
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