package com.example.myapplication.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveHikeListIdProductsInMeal")
data class ArchiveHikeListIdProductsInMeal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "idMeal")
    val idMeal: Int,
    @ColumnInfo(name = "idProductsInMeal")
    val idProductsInMeal: Int,
    @ColumnInfo(name = "nameProducts")
    val nameProducts: String
)
