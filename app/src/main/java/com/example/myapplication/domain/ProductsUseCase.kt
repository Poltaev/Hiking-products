package com.example.myapplication.domain

import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.ListWithProducts
import com.example.myapplication.dataBase.products.ProductStorage
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.products.StoregeWithList
import kotlinx.coroutines.flow.Flow

class ProductsUseCase(private val hikeDao: HikeDao) {
    // ListProducts
    suspend fun getAllListProductsFlow(): Flow<List<ListProducts>> {
        return hikeDao.getAllListProductsFlow()
    }

    suspend fun getAllListProductsList(): List<ListProducts> {
        return hikeDao.getAllListListProducts()
    }

    suspend fun loadListProducts(
        listId: Int,
        productsId: Int
    ) {
        val listProducts = ListProducts(
            listId = listId,
            productsId = productsId
        )
        hikeDao.insertOneListProducts(listProducts)
    }

    suspend fun deleteListProducts(listProducts: ListProducts) {
        hikeDao.deleteOneListProducts(listProducts)
    }

    suspend fun upDateListProducts(
        listId: Int,
        productsId: Int
    ) {
        val listProducts = ListProducts(
            listId = listId,
            productsId = productsId
        )
        hikeDao.updateOneListProducts(listProducts)
    }
    // StoregeWithList
    suspend fun getAllListTypeOfProductsFlow(): Flow<List<ListTypeOfProducts>> {
        return hikeDao.getAllListTypeOfProductsFlow()
    }

    suspend fun getAllListTypeOfProductsList(): List<ListTypeOfProducts> {
        return hikeDao.getAllListListTypeOfProducts()
    }

    suspend fun loadListTypeOfProducts(
        id: Int,
        idProductStorage: Int,
        typeOfMeal: String,
        name: String
    ) {
        val listTypeOfProducts = ListTypeOfProducts(
            id = id,
            idProductStorage = idProductStorage,
            typeOfMeal = typeOfMeal,
            name = name
        )
        hikeDao.insertOneListTypeOfProducts(listTypeOfProducts)
    }

    suspend fun deleteListTypeOfProducts(storegeWithList: ListTypeOfProducts) {
        hikeDao.deleteOneListTypeOfProducts(storegeWithList)
    }

    suspend fun upDateListTypeOfProducts(
        id: Int,
        idProductStorage: Int,
        typeOfMeal: String,
        name: String
    ) {
        val listTypeOfProducts = ListTypeOfProducts(
            id = id,
            idProductStorage = idProductStorage,
            typeOfMeal = typeOfMeal,
            name = name
        )
        hikeDao.updateOneListTypeOfProducts(listTypeOfProducts)
    }

    // ProductStorage
    suspend fun getAllProductStorageFlow(): Flow<List<ProductStorage>> {
        return hikeDao.getAllProductStorageFlow()
    }

    suspend fun getAllProductStorageList(): List<ProductStorage> {
        return hikeDao.getAllListProductStorage()
    }

    suspend fun loadProductStorage(
        id: Int,
        name: String
    ) {
        val productStorageForLoad = ProductStorage(
            id = id,
            name = name
        )
        hikeDao.insertOneProductStorage(productStorageForLoad)
    }

    suspend fun deleteProductStorage(productStorage: ProductStorage) {
        hikeDao.deleteOneProductStorage(productStorage)
    }

    suspend fun upDateProductStorage(
        id: Int,
        name: String
    ) {
        val productStorage = ProductStorage(
            id = id,
            name = name
        )
        hikeDao.updateOneProductStorage(productStorage)
    }

    // Products
    suspend fun getAllProductsFlow(): Flow<List<Products>> {
        return hikeDao.getAllProductsFlow()
    }

    suspend fun getAllProductsList(): List<Products> {
        return hikeDao.getAllListProducts()
    }

    suspend fun loadProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val ProductsForLoad = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
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
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val products = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
        hikeDao.updateOneProducts(products)
    }
}