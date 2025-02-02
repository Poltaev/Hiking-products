package com.example.myapplication.dataBase.archive

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.archive.ArchiveStorage

data class ArchiveHikeListIdProductsInMealEmbeded(
    @Embedded
    val meal : ArchiveHikeMealIntakeSheet,
    @Relation(
        entity = ArchiveHikeListIdProductsInMeal::class,
        parentColumn = "id",
        entityColumn = "idMeal"
    )
    val listIdProductsInMeal : List<ArchiveHikeListIdProductsInMeal>
)
