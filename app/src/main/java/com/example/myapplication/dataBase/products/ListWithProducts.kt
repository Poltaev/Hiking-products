package com.example.myapplication.dataBase.products

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ListWithProducts(
    @Embedded
    val listtypeOfProduct: ListTypeOfProducts,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ListProducts::class,
            parentColumn = "list_Id",
            entityColumn = "products_Id"
        )
    )
    val listWithproduct: List<Products>
)
