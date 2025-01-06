package com.example.myapplication.ui.adding_a_type_list_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

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