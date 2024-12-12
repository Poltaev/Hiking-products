package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Participants")
data class Participants(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "photo")
    val photo : String,
    @ColumnInfo(name = "firstName")
    val firstName : String,
    @ColumnInfo(name = "lastName")
    val lastName : String,
    @ColumnInfo(name = "gender")
    val gender : String,
    @ColumnInfo(name = "age")
    val age : Int,
    @ColumnInfo(name = "maximum_portable_weight")
    val maximumPortableWeight : Double,
    @ColumnInfo(name = "weight_of_personal_items")
    val weightOfPersonalItems : Double,
    @ColumnInfo(name = "participation_in_the_campaign")
    val participationInTheCampaign : Boolean
)
