package com.example.hike_menu_calculator.dataBase.archive

import androidx.room.Embedded
import androidx.room.Relation

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
