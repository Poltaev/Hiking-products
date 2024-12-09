package com.example.myapplication.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductsDao {
    @Query("SELECT * FROM Products")
    fun getAllProducts () {}

    fun getOneProducts () {}

    @Insert
    fun insertOneProducts () {}

    @Delete
    fun deleteOneProducts () {}

    @Update
    fun updateOneProducts () {}
}