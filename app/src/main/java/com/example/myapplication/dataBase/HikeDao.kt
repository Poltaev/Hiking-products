package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.ListWithProducts
import com.example.myapplication.dataBase.products.ProductStorage
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.products.StoregeWithList
import kotlinx.coroutines.flow.Flow

@Dao
interface HikeDao {
    // List_With_Products
    @Transaction
    @Query("SELECT * FROM List_With_Products")
    fun getAllListProductsFlow(): Flow<List<ListProducts>>

    @Query("SELECT * FROM List_With_Products")
    fun getAllListListProducts(): List<ListProducts>

    @Insert
    suspend fun insertOneListProducts(storegeWithList: ListProducts)

    @Delete
    suspend fun deleteOneListProducts(storegeWithList: ListProducts)

    @Update
    suspend fun updateOneListProducts(products: ListProducts)

    // ListTypeOfProducts
    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListTypeOfProductsFlow(): Flow<List<ListTypeOfProducts>>

    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListListTypeOfProducts(): List<ListTypeOfProducts>

    @Insert
    suspend fun insertOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Delete
    suspend fun deleteOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Update
    suspend fun updateOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

//    // ListWithProducts
//    @Transaction
//    @Query("SELECT * FROM List_Type_Of_Products")
//    fun getAllListWithProductsFlow(): Flow<List<ListWithProducts>>
//
//    @Query("SELECT * FROM List_Type_Of_Products")
//    fun getAllListListWithProducts(): List<ListWithProducts>
//
//    @Insert
//    suspend fun insertOneListWithProducts(listWithProducts: ListWithProducts)
//
//    @Delete
//    suspend fun deleteOneListWithProducts(listWithProducts: ListWithProducts)
//
//    @Update
//    suspend fun updateOneListWithProducts(listWithProducts: ListWithProducts)

//    // StoregeWithList
//    @Transaction
//    @Query("SELECT * FROM Product_Storage")
//    fun getAllStoregeWithListFlow(): Flow<List<StoregeWithList>>
//
//    @Query("SELECT * FROM Product_Storage")
//    fun getAllListStoregeWithList(): List<StoregeWithList>
//
//    @Insert
//    suspend fun insertOneStoregeWithList(storegeWithList: StoregeWithList)
//
//    @Delete
//    suspend fun deleteOneStoregeWithList(storegeWithList: StoregeWithList)
//
//    @Update
//    suspend fun updateOneStoregeWithList(products: StoregeWithList)

//    // ProductStorage
//    @Query("SELECT * FROM Product_Storage")
//    fun getAllProductStorageFlow(): Flow<List<ProductStorage>>
//
//    @Query("SELECT * FROM Product_Storage")
//    fun getAllListProductStorage(): List<ProductStorage>
//
//    @Insert
//    suspend fun insertOneProductStorage(productStorage: ProductStorage)
//
//    @Delete
//    suspend fun deleteOneProductStorage(productStorage: ProductStorage)
//
//    @Update
//    suspend fun updateOneProductStorage(productStorage: ProductStorage)

    // Products
    @Query("SELECT * FROM Products")
     fun getAllProductsFlow(): Flow<List<Products>>

    @Query("SELECT * FROM Products")
    fun getAllListProducts(): List<Products>

    @Insert
    suspend fun insertOneProducts(products: Products)

    @Delete
    suspend fun deleteOneProducts(products: Products)

    @Update
    suspend fun updateOneProducts(products: Products)

    // Equipment
    @Query("SELECT * FROM Equipment")
    fun getAllEquipmentFlow(): Flow<List<Equipment>>

    @Query("SELECT * FROM Equipment")
    fun getAllListEquipment(): List<Equipment>

    @Insert
    suspend fun insertOneEquipment(equipment: Equipment)

    @Delete
    suspend fun deleteOneEquipment(equipment: Equipment)

    @Update
    suspend fun updateOneEquipment(equipment: Equipment)

    // Participants
    @Query("SELECT * FROM Participants")
    fun getAllParticipantsFlow(): Flow<List<Participants>>

    @Query("SELECT * FROM Participants")
    fun getAllListParticipants(): List<Participants>

    @Insert
    suspend fun insertOneParticipant(participant: Participants)

    @Delete
    suspend fun deleteOneParticipant(participant: Participants)

    @Update
    suspend fun updateOneParticipant(participant: Participants)

}