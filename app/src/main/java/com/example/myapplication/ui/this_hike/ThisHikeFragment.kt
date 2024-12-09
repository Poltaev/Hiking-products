package com.example.myapplication.ui.this_hike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.dataBase.App
import com.example.myapplication.databinding.FragmentThisHikeBinding
import com.example.myapplication.ui.hike_archive.HikeArchiveViewModel
import kotlinx.coroutines.launch

class ThisHikeFragment : Fragment() {

    private var _binding: FragmentThisHikeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ThisHikeViewModel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                val productsDao = (requireContext().applicationContext as App).db.userDao()
                return ThisHikeViewModel(productsDao) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThisHikeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addProducts()
        lifecycleScope.launch {
            viewModel.allProducts.collect{ product ->
                binding.textView.text = product.joinToString(separator = "\r\n")

            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}