package com.example.myapplication.dataBase

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FoodForMealsWithProducts(
    @Embedded
    val foodForMeals: FoodForMeals,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            FoodForMealsProducts::class,
            parentColumn = "food_for_meals_id",
            entityColumn = "products_id"
        )
    )
    val equipment: List<Products>
)
