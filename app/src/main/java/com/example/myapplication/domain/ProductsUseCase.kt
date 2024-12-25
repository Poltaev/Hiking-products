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
        name: String
    ) {
        val listTypeOfProducts = ListTypeOfProducts(
            id = id,
            idProductStorage = idProductStorage,
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
        name: String
    ) {
        val listTypeOfProducts = ListTypeOfProducts(
            id = id,
            idProductStorage = idProductStorage,
            name = name
        )
        hikeDao.updateOneListTypeOfProducts(listTypeOfProducts)
    }
//      // StoregeWithList
//    suspend fun getAllListWithProductsFlow(): Flow<List<ListWithProducts>> {
//        return hikeDao.getAllListWithProductsFlow()
//    }
//
//    suspend fun getAllListWithProductsList(): List<ListWithProducts> {
//        return hikeDao.getAllListListWithProducts()
//    }
//
//    suspend fun loadListWithProducts(
//        listtypeOfProduct: ListTypeOfProducts,
//        listWithproduct: List<Products>
//    ) {
//        val listWithProducts = ListWithProducts(
//            listtypeOfProduct = listtypeOfProduct,
//            listWithproduct = listWithproduct
//        )
//        hikeDao.insertOneListWithProducts(listWithProducts)
//    }
//
//    suspend fun deleteListWithProducts(listWithProducts: ListWithProducts) {
//        hikeDao.deleteOneListWithProducts(listWithProducts)
//    }
//
//    suspend fun upDateStoregeWithList(
//        listtypeOfProduct: ListTypeOfProducts,
//        listWithproduct: List<Products>
//    ) {
//        val listWithProducts = ListWithProducts(
//            listtypeOfProduct = listtypeOfProduct,
//            listWithproduct = listWithproduct
//        )
//        hikeDao.updateOneListWithProducts(listWithProducts)
//    }
//    // StoregeWithList
//    suspend fun getAllStoregeWithListFlow(): Flow<List<StoregeWithList>> {
//        return hikeDao.getAllStoregeWithListFlow()
//    }
//
//    suspend fun getAllStoregeWithListList(): List<StoregeWithList> {
//        return hikeDao.getAllListStoregeWithList()
//    }
//
//    suspend fun loadStoregeWithList(
//        productStorage: ProductStorage,
//        listTypeOfProducts: List<ListWithProducts>
//    ) {
//        val storegeWithListForLoad = StoregeWithList(
//            productStorage = productStorage,
//            listTypeOfPrducts = listTypeOfProducts
//        )
//        hikeDao.insertOneStoregeWithList(storegeWithListForLoad)
//    }
//
//    suspend fun deleteStoregeWithList(storegeWithList: StoregeWithList) {
//        hikeDao.deleteOneStoregeWithList(storegeWithList)
//    }
//
//    suspend fun upDateStoregeWithList(
//        productStorage: ProductStorage,
//        listTypeOfProducts: List<ListWithProducts>
//    ) {
//        val storegeWithList = StoregeWithList(
//            productStorage = productStorage,
//            listTypeOfPrducts = listTypeOfProducts
//        )
//        hikeDao.updateOneStoregeWithList(storegeWithList)
//    }
//    // ProductStorage
//    suspend fun getAllProductStorageFlow(): Flow<List<ProductStorage>> {
//        return hikeDao.getAllProductStorageFlow()
//    }
//
//    suspend fun getAllProductStorageList(): List<ProductStorage> {
//        return hikeDao.getAllListProductStorage()
//    }
//
//    suspend fun loadProductStorage(
//        id: Int,
//        name: String
//    ) {
//        val ProductStorageForLoad = ProductStorage(
//            id = id,
//            name = name
//        )
//        hikeDao.insertOneProductStorage(ProductStorageForLoad)
//    }
//
//    suspend fun deleteProductStorage(productStorage: ProductStorage) {
//        hikeDao.deleteOneProductStorage(productStorage)
//    }
//
//    suspend fun upDateProductStorage(
//        id: Int,
//        name: String
//    ) {
//        val productStorage = ProductStorage(
//            id = id,
//            name = name
//        )
//        hikeDao.updateOneProductStorage(productStorage)
//    }
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