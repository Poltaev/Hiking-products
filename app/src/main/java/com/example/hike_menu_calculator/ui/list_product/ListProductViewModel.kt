package com.example.hike_menu_calculator.ui.list_product

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.ListProducts
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class ListProductViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend  fun getAllProductFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }

    suspend  fun getAllListWithProduct(): List<ListProducts> {
        return ProductsUseCase(hikeDao).getAllListProductsList()
    }
    suspend  fun getAllListTypeOfProduct(): List<ListTypeOfProducts> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsList()
    }
    suspend fun deleteListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts){
        return ProductsUseCase(hikeDao).deleteListTypeOfProducts(listTypeOfProducts)
    }
}