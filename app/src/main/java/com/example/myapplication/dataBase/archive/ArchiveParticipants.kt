package com.example.myapplication.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveParticipants")
data class ArchiveParticipants(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "hikeId")
    val hikeId : Int,
    @ColumnInfo(name = "photo")
    val photo : String,
    @ColumnInfo(name = "firstName")
    val firstName : String,
    @ColumnInfo(name = "lastName")
    val lastName : String,
    @ColumnInfo(name = "gender")
    val gender : String,
    @ColumnInfo(name = "age")
    val age : String,
    @ColumnInfo(name = "comment")
    val comment : String
)
