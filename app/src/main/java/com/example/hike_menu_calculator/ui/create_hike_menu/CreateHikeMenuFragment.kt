package com.example.hike_menu_calculator.ui.create_hike_menu

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeEquipmentBinding
import com.example.hike_menu_calculator.databinding.FragmentCreateHikeMenuBinding
import com.example.hike_menu_calculator.ui.adapters.CreateHikeEquipmentAdapter
import com.example.hike_menu_calculator.ui.adapters.CreateStorageProductsAdapter
import com.example.hike_menu_calculator.ui.adapters.ListStorageProductsAdapter
import com.example.hike_menu_calculator.ui.create_hike_equipment.CreateHikeEquipmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CreateHikeMenuFragment : Fragment() {
    lateinit var job: Job

    private var _binding: FragmentCreateHikeMenuBinding? = null

    private val binding get() = _binding!!

    private var adapter: CreateStorageProductsAdapter? = null

    private var idStorage = 1
    private val viewModel: CreateHikeMenuViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return CreateHikeMenuViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateHikeMenuBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllStorageListFlow().collect {
                idStorage = it.first().id
                adapter = CreateStorageProductsAdapter(it) { onItemClick(it) }
                launch(Dispatchers.Main) {
                    binding.recyclerViewListMenu.adapter = adapter
                }

            }
        }
        binding.buttonFurther.setOnClickListener {
            val bundle = Bundle().apply {
                idStorage.let { putInt("storageId", it) }
            }
            findNavController().navigate(
                R.id.action_createHikeMenuFragment_to_createHikeProductsInListFragment,
                bundle
            )
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

    private fun onItemClick(item: ProductStorage) {
        idStorage = item.id
        val toast = Toast.makeText(
            requireContext().applicationContext,
            "Меню: ${item.name}, выбрано",
            Toast.LENGTH_SHORT
        )
        toast.show()
    }
}