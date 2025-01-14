package com.example.myapplication.dataBase.thisHike

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ThisHikeMealIntakeWithProducts(
    @Embedded
    val mealIntake: ThisHikeMealIntakeSheet,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ThisHikeProductsMealList::class,
            parentColumn = "meal_Intake_id",
            entityColumn = "this_products_id"
        )
    )
    val thisHikeProducts: List<ThisHikeProducts>,
)
