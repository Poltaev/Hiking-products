package com.example.myapplication.ui.add_products_in_list

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow

class AddProductsInListViewModel(private val hikeDao: HikeDao) : ViewModel() {

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
    suspend fun deleteProductsWithList(listProducts: ListProducts){
        return ProductsUseCase(hikeDao).deleteListProducts(listProducts)
    }
    suspend fun addProductsWithList(
        listId: Int,
        productsId: Int
    ){
        return ProductsUseCase(hikeDao).loadListProducts(listId, productsId)
    }
}