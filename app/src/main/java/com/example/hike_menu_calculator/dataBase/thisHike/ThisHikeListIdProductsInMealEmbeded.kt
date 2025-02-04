package com.example.hike_menu_calculator.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Relation

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
