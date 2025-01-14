package com.example.myapplication.dataBase.thisHike

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ThisHikeParticipants")
data class ThisHikeParticipants(
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
    @ColumnInfo(name = "maximum_portable_weight")
    val maximumPortableWeight : Int,
    @ColumnInfo(name = "weight_of_personal_items")
    val weightOfPersonalItems : Int,
    @ColumnInfo(name = "comment")
    val comment : String
)
