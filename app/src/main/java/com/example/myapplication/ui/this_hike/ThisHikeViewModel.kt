package com.example.myapplication.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Products
import com.example.myapplication.dataBase.ProductsDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThisHikeViewModel(private val productDao: ProductsDao) : ViewModel() {
    val allProducts = this.productDao.getAllProducts()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
    fun addProducts() {
        viewModelScope.launch {
            productDao.insertOneProducts(
                Products(
                    id = 1,
                    name = "name",
                    weightForPerson = 1.0,
                    packageWeight = 1.0,
                    thePhaseOfEating = "breakfeast",
                    incompletePurchase = false,
                    fullPurchase = false,
                    colorOfBackground = "red",
                    weWillUseItInTheCurrentCampaign = false
                )
            )
        }
    }
}