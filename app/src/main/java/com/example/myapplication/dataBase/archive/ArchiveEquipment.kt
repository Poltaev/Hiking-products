package com.example.myapplication.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveEquipment")
data class ArchiveEquipment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "hikeId")
    val hikeId : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "photo")
    val photo : String,
    @ColumnInfo(name = "weight")
    val weight : Int,
    @ColumnInfo(name = "comment")
    val comment : String
)
