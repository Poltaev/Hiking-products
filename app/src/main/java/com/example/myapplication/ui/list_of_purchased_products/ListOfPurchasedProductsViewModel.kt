package com.example.myapplication.ui.list_of_purchased_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListOfPurchasedProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {


}