package com.example.hike_menu_calculator.dataBase.thisHike

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "ThisHikeProductsMealList",
    primaryKeys = ["meal_Intake_id","this_products_id"]
)
data class ThisHikeProductsMealList(
    @ColumnInfo(name = "meal_Intake_id")
    val mealIntakeId : Int,
    @ColumnInfo(name = "this_products_id")
    val productsId : Int
)
