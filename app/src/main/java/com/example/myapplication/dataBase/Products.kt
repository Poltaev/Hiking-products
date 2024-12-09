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
    val weightForPerson : Double,
    @ColumnInfo(name = "package_weight")
    val packageWeight : Double,
    @ColumnInfo(name = "the_phase_of_eating")
    val thePhaseOfEating : String,
    @ColumnInfo(name = "incomplete_purchase")
    val incompletePurchase : Boolean,
    @ColumnInfo(name = "full_purchase")
    val fullPurchase : Boolean,
    @ColumnInfo(name = "color_of_background")
    val colorOfBackground : String,
    @ColumnInfo(name = "we_will_use_it_in_the_current_campaign")
    val weWillUseItInTheCurrentCampaign : Boolean

)
