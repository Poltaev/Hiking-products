package com.example.hike_menu_calculator.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveHike")
data class ArchiveHike(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "storageId")
    val storageId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "number_of_day")
    val numberOfDay: Int,
    @ColumnInfo(name = "number_of_snacks_in_day")
    val numberOfSnacksInDay: Int
)
