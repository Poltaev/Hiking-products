package com.example.myapplication.ui.list_all_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class ListAllProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend  fun getAllProductFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }
}