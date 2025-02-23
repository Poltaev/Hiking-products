package com.example.hike_menu_calculator.ui.add_comment

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
import com.example.hike_menu_calculator.R
import com.example.hike_menu_calculator.dataBase.App
import com.example.hike_menu_calculator.databinding.FragmentAddCommentBinding
import com.example.hike_menu_calculator.databinding.FragmentThisHikeListOfProductsBinding
import com.example.hike_menu_calculator.ui.this_hike_list_of_products.ThisHikeListOfProductsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddCommentFragment : Fragment() {
    private var id = 1
    private var comment = "Без комментариев"
    private var _binding: FragmentAddCommentBinding? = null

    private val binding get() = _binding!!


    private val viewModel: AddCommentViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val hikeDao = (requireContext().applicationContext as App).db.hikeDao()
                return AddCommentViewModel(hikeDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt("productTypeId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddCommentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enterComment.doOnTextChanged { text, _, _, _ ->
            if (text == null) {
                comment
            } else {
                comment = text.toString()
            }
        }
        binding.buttonSaveComment.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.upDateComment(id, comment)
            }
            val toast = Toast.makeText(
                requireContext().applicationContext,
                "Комментарий добален",
                Toast.LENGTH_SHORT
            )
            toast.show()
            findNavController().navigate(
                R.id.action_addCommentFragment_to_thisHikeListOfProductsFragment
            )
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}