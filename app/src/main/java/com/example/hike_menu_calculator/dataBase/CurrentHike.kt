package com.example.hike_menu_calculator.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrentHike")
data class CurrentHike(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "the_distance_to_be_covered_in_the_campaign")
    val theDistanceToBeCoveredInTheCampaign : Int,
    @ColumnInfo(name = "full_days")
    val fullDays : Int,
    @ColumnInfo(name = "number_of_snacks_per_day")
    val numberOfSnacksPerDay : Int
)
