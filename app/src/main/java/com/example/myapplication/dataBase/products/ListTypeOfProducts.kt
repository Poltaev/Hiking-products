package com.example.myapplication.dataBase.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "List_Type_Of_Products")
data class ListTypeOfProducts(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "id_Product_Storage")
    val idProductStorage: Int,
    @ColumnInfo(name = "name")
    val name: String

)
