package com.example.myapplication.dataBase.products

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "List_With_Products",
    primaryKeys = ["list_Id","products_Id"]
)
data class ListProducts(
    @ColumnInfo(name = "list_Id")
    val listId: Int,
    @ColumnInfo(name = "products_Id")
    val productsId: Int
)
