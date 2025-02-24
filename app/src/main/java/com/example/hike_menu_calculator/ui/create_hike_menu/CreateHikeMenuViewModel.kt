package com.example.hike_menu_calculator.ui.create_hike_menu

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class CreateHikeMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllStorageListFlow(): Flow<List<ProductStorage>> {
        return ProductsUseCase(hikeDao).getAllProductStorageFlow()
    }
}