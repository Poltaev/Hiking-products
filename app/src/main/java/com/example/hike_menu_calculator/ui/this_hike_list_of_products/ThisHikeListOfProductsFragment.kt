package com.example.hike_menu_calculator.ui.this_hike_list_of_products

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.databinding.FragmentThisHikeListOfProductsBinding
import com.example.hike_menu_calculator.ui.adapters.ThisHikeProductsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ThisHikeListOfProductsFragment : Fragment() {
    lateinit var job: Job
    private var _binding: FragmentThisHikeListOfProductsBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeListOfProductsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return ThisHikeListOfProductsViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentThisHikeListOfProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        job = lifecycleScope.launch {
            viewModel.getAllProductsFlow().collect {
                val listProducts = it
                val nameParticipant = mutableListOf<String>()
                runBlocking(Dispatchers.IO) {
                    val item = viewModel.getAllProductsParticipant()
                    listProducts.forEach { itemProduct ->
                        item.forEach { itemProductParticipant ->
                            if (itemProduct.id == itemProductParticipant.productsId) {
                                val participantList = viewModel.getAllPartisipant()
                                participantList.forEach { itemParticipant ->
                                    if (itemProductParticipant.participantId == itemParticipant.id) {
                                        nameParticipant.add(itemParticipant.firstName + " " + itemParticipant.lastName)
                                    }
                                }
                            }
                        }
                    }
                }
                val typeListAdapter = listProducts.let {
                    ThisHikeProductsAdapter(it, nameParticipant) { onItemClick(it) }
                }
                binding.recyclerViewListProducts.adapter = typeListAdapter

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

    private fun onItemClick(item: ThisHikeProducts) {
        val bundle = Bundle().apply {
            item.id.let { putInt("productTypeId", it) }
        }
        findNavController().navigate(
            R.id.action_thisHikeListOfProductsFragment_to_passOnOneThingFragment,
            bundle
        )
    }
}