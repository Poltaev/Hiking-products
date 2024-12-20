package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "food_for_meals_products",
    primaryKeys = ["food_for_meals_id","products_id"]
)
data class FoodForMealsProducts(
    @ColumnInfo(name = "food_for_meals_id")
    val foodForMealsId: Int,
    @ColumnInfo(name = "products_id")
    val productsId: Int
)
