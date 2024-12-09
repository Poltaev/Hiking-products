package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Products(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "weight_per_person")
    val weightPerPerson : Double,
    @ColumnInfo(name = "package_weight")
    val packageWeight : Int

)
