package com.example.myapplication.dataBase.products

import androidx.room.Embedded
import androidx.room.Relation

data class StoregeWithList(
    @Embedded
    val productStorage: ProductStorage,
    @Relation(
        entity = ListTypeOfProducts::class,
        parentColumn = "id",
        entityColumn = "id_ProductStorage"
    )
    val listTypeOfPrducts: List<ListWithProducts>
)
