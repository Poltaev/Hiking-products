package com.example.hike_menu_calculator.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveHikeMealIntakeSheet")
data class ArchiveHikeMealIntakeSheet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "hikeId")
    val hikeId : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "numberOfday")
    val numberOfday: Int,
    @ColumnInfo(name = "typeMeal")
    val typeMeal: String
)
