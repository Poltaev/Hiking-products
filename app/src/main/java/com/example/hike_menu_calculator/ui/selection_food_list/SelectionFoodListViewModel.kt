package com.example.hike_menu_calculator.ui.selection_food_list

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class SelectionFoodListViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllTypeListFlow(): Flow<List<ListTypeOfProducts>> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsFlow()
    }


}