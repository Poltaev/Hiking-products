package com.example.myapplication.dataBase.archive

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArchiveHikeMealIntakeWithProducts(
    @Embedded
    val mealIntake: ArchiveHikeMealIntakeSheet,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            ArchiveHikeProductsMealList::class,
            parentColumn = "meal_Intake_id",
            entityColumn = "archive_products_id"
        )
    )
    val thisHikeProducts: List<ArchiveProducts>,
)
