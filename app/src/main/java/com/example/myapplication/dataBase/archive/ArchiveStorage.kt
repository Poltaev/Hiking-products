package com.example.myapplication.dataBase.archive

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ArchiveStorage")
data class ArchiveStorage(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String
)