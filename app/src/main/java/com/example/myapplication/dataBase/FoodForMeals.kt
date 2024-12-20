package com.example.myapplication.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FoodForMeals")
data class FoodForMeals(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "porridge_for_breakfast")
    val porridgeForBreakfast: List<Products>,
    @ColumnInfo(name = "dried_fruits_for_breakfast")
    val driedFruitsForBreakfast: List<Products>,
    @ColumnInfo(name = "butter_for_breakfast")
    val butterForBreakfast: List<Products>,
    @ColumnInfo(name = "condensed_milk_for_breakfast")
    val condensedMilkForBreakfast: List<Products>,
    @ColumnInfo(name = "bread_for_breakfast")
    val breadForBreakfast: List<Products>,
    @ColumnInfo(name = "a_drink_for_breakfast")
    val aDrinkForBreakfast: List<Products>,
    @ColumnInfo(name = "runningSnack")
    val runningSnack: List<Products>,
    @ColumnInfo(name = "grits_for_lunch")
    val gritsForLunch: List<Products>,
    @ColumnInfo(name = "addition_to_the_lunch_cereal")
    val additionToTheLunchCereal: List<Products>,
    @ColumnInfo(name = "sweets_for_lunch")
    val sweetsForLunch: List<Products>,
    @ColumnInfo(name = "bread_for_lunch")
    val breadForLunch: List<Products>,
    @ColumnInfo(name = "a_drink_for_lunch")
    val aDrinkForLunch: List<Products>,
    @ColumnInfo(name = "the_basis_of_the_soup_for_dinner")
    val theBasisOfTheSoupForDinner: List<Products>,
    @ColumnInfo(name = "meat_for_soup_for_dinner")
    val meatForSoupForDinner: List<Products>,
    @ColumnInfo(name = "grits_in_soup_for_dinner")
    val gritsInSoupForDinner: List<Products>,
    @ColumnInfo(name = "grits_for_dinner")
    val gritsForDinner: List<Products>,
    @ColumnInfo(name = "meat_for_dinner")
    val meatForDinner: List<Products>,
    @ColumnInfo(name = "bread_for_dinner")
    val breadForDinner: List<Products>,
    @ColumnInfo(name = "a_drink_for_dinner")
    val aDrinkForDinner: List<Products>,
    @ColumnInfo(name = "spices")
    val spices: List<Products>

)
