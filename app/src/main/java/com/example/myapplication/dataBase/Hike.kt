package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hike")
data class Hike(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "full_days")
    val fullDays : Int,
    @ColumnInfo(name = "days_with_a_radial_output")
    val daysWithARadialOutput : Int,
    @ColumnInfo(name = "part_of_the_day")
    val partOfTheDay : Int
)
