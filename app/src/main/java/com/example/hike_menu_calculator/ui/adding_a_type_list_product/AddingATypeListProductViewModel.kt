package com.example.hike_menu_calculator.ui.adding_a_type_list_product

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class AddingATypeListProductViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend  fun getAllListTypeOfProductsFlow(): Flow<List<ListTypeOfProducts>> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsFlow()
    }

    suspend  fun getAllListTypeOfProductsList(): List<ListTypeOfProducts> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsList()

    }

    suspend fun deleteListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts){
        return ProductsUseCase(hikeDao).deleteListTypeOfProducts(listTypeOfProducts)
    }

    suspend fun addListTypeOfProducts(
        id: Int,
        typeOfMeal: String,
        name: String
    ) {
        ProductsUseCase(hikeDao).loadListTypeOfProducts(
            id = id,
            idProductStorage = 1,
            typeOfMeal = typeOfMeal,
            name = name
        )
    }

}