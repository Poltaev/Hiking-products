package com.example.myapplication.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(
    tableName = "ArchiveHikeProductsMealList",
    primaryKeys = ["meal_Intake_id","archive_products_id"]
)
data class ArchiveHikeProductsMealList(
    @ColumnInfo(name = "meal_Intake_id")
    val mealIntakeId : Int,
    @ColumnInfo(name = "archive_products_id")
    val productsId : Int
)
