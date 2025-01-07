package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneListProducts(storegeWithList: ListProducts)

    @Delete
    suspend fun deleteOneListProducts(storegeWithList: ListProducts)

    @Update
    suspend fun updateOneListProducts(storegeWithList: ListProducts)

    // ListTypeOfProducts
    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListTypeOfProductsFlow(): Flow<List<ListTypeOfProducts>>

    @Query("SELECT * FROM List_Type_Of_Products")
    fun getAllListListTypeOfProducts(): List<ListTypeOfProducts>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Delete
    suspend fun deleteOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    @Update
    suspend fun updateOneListTypeOfProducts(listTypeOfProducts: ListTypeOfProducts)

    // ProductStorage
    @Query("SELECT * FROM Product_Storage")
    fun getAllProductStorageFlow(): Flow<List<ProductStorage>>

    @Query("SELECT * FROM Product_Storage")
    fun getAllListProductStorage(): List<ProductStorage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneProductStorage(productStorage: ProductStorage)

    @Delete
    suspend fun deleteOneProductStorage(productStorage: ProductStorage)

    @Update
    suspend fun updateOneProductStorage(productStorage: ProductStorage)

    // Products
    @Query("SELECT * FROM Products")
     fun getAllProductsFlow(): Flow<List<Products>>

    @Query("SELECT * FROM Products")
    fun getAllListProducts(): List<Products>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneParticipant(participant: Participants)

    @Delete
    suspend fun deleteOneParticipant(participant: Participants)

    @Update
    suspend fun updateOneParticipant(participant: Participants)

}