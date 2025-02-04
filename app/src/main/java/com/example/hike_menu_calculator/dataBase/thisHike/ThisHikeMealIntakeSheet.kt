package com.example.hike_menu_calculator.dataBase.thisHike

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ThisHikeMealIntakeSheet")
data class ThisHikeMealIntakeSheet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "hikeId")
    val hikeId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "numberOfday")
    val numberOfday: Int,
    @ColumnInfo(name = "typeMeal")
    val typeMeal: String
)
