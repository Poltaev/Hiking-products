package com.example.myapplication.ui.adding_a_type_list_product

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentAddingATypeListProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddingATypeListProductFragment : Fragment() {
    private var id = 1
    private var typeOfMeal = "Завтрак"
    private var nameProductsList = "Каша"

    private var _binding: FragmentAddingATypeListProductBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AddingATypeListProductViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddingATypeListProductViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("listTypeId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingATypeListProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (id != 9999) {
            lifecycleScope.launch(Dispatchers.IO) {
                val listTypeOfProducts = viewModel.getAllListTypeOfProductsList()
                listTypeOfProducts.forEach {
                    if (it.id == id) {
                        id = it.id
                        typeOfMeal = it.typeOfMeal
                        nameProductsList = it.name
                    }
                }
            }
        }
        binding.enterNameListProduct.doOnTextChanged { text, _, _, _ ->
            if (text == null) {
                nameProductsList
            } else {
                nameProductsList = text.toString()
            }
        }
        binding.buttonSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val typeOfProductsList = viewModel.getAllListTypeOfProductsList()
                val id = typeOfProductsList[typeOfProductsList.size - 1].id + 1
                typeOfMeal = binding.spinnerTypeMenu.selectedItem.toString()
                viewModel.addListTypeOfProducts(
                    id,
                    typeOfMeal,
                    nameProductsList
                )
            }
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Список создан",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}