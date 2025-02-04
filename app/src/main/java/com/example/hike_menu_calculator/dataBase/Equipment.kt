package com.example.hike_menu_calculator.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Equipment")
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "name")
    val name : String,
    @ColumnInfo(name = "photo")
    val photo : String,
    @ColumnInfo(name = "weight")
    val weight : Int,
    @ColumnInfo(name = "the_volume_item")
    val theVolumeItem : Boolean,
    @ColumnInfo(name = "theSoleOwner")
    val theSoleOwner : Boolean,
    @ColumnInfo(name = "nameOwner")
    val nameOwner : String,
    @ColumnInfo(name = "idOwner")
    val idOwner : Int,
    @ColumnInfo(name = "equipment_in_the_campaign")
    val equipmentInTheCampaign : Boolean

)
