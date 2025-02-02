package com.example.myapplication.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.archive.ArchiveStorage

data class ThisHikeListIdProductsInMealEmbeded(
    @Embedded
    val meal : ThisHikeMealIntakeSheet,
    @Relation(
        entity = ThisHikeListIdProductsInMeal::class,
        parentColumn = "id",
        entityColumn = "idMeal"
    )
    val listIdProductsInMeal : List<ThisHikeListIdProductsInMeal>
)
