package com.example.myapplication.domain

import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Products
import kotlinx.coroutines.flow.Flow

class ProductsUseCase(private val hikeDao: HikeDao) {

    // Products
    suspend fun getAllCollectionProductsFlow(): Flow<List<Products>> {
        return hikeDao.getAllProductsFlow()
    }

    suspend fun getAllCollectionProductsList(): List<Products> {
        return hikeDao.getAllListProducts()
    }

    suspend fun loadProducts(
        id: Int,
        name: String,
        weightForPerson: Double,
        packageWeight: Double,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val ProductsForLoad = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
        hikeDao.insertOneProducts(ProductsForLoad)
    }

    suspend fun deleteProducts(products: Products) {
        hikeDao.deleteOneProducts(products)
    }

    suspend fun upDateProducts(
        id: Int,
        name: String,
        weightForPerson: Double,
        packageWeight: Double,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val products = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
        hikeDao.updateOneProducts(products)
    }
}