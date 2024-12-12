package com.example.myapplication.ui.list_of_purchased_products

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R

class ListOfPurchasedProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ListOfPurchasedProductsFragment()
    }

    private val viewModel: ListOfPurchasedProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_of_purchased_products, container, false)
    }
}