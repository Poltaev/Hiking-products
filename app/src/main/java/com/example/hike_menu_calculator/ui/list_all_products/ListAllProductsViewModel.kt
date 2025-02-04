package com.example.hike_menu_calculator.ui.list_all_products

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class ListAllProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend  fun getAllProductFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }
}