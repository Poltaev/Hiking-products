package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    @Query("SELECT * FROM Products")
     fun getAllProducts(): Flow<List<Products>>

//     fun getOneProducts(products: Products)

    @Insert
    suspend fun insertOneProducts(products: Products)

    @Delete
    suspend fun deleteOneProducts(products: Products)

    @Update
    suspend fun updateOneProducts(products: Products)
}