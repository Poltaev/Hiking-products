package com.example.myapplication.dataBase.thisHike

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ThisHikeProducts")
data class ThisHikeProducts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "weight_per_person")
    val weightForPerson : Int,
    @ColumnInfo(name = "package_weight")
    val packageWeight : Int,
    @ColumnInfo(name = "the_weight_of_one_meal")
    val theWeightOfOneMeal : Int,
    @ColumnInfo(name = "weight_on_the_hike")
    val weightOnTheHike : Int,
    @ColumnInfo(name = "remaining_weight")
    val remainingWeight : Int,
    @ColumnInfo(name = "partially_assembled")
    val partiallyAssembled : Boolean,
    @ColumnInfo(name = "fully_assembled")
    val fullyAssembled : Boolean,
    @ColumnInfo(name = "the_volume_item")
    val theVolumeItem : Boolean,
    @ColumnInfo(name = "comment")
    val comment : String
)
