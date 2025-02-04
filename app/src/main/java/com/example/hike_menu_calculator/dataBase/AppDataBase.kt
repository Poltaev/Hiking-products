package com.example.hike_menu_calculator.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.dataBase.archive.ArchiveStorage
import com.example.hike_menu_calculator.dataBase.products.ListProducts
import com.example.hike_menu_calculator.dataBase.products.ListTypeOfProducts
import com.example.hike_menu_calculator.dataBase.products.ProductStorage
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHike
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants

@Database(
    entities = [
        Products::class,
        CurrentHike::class,
        Equipment::class,
        Participants::class,
        ProductStorage::class,
        ListTypeOfProducts::class,
        ListProducts::class,
        ArchiveProducts::class,
        ArchiveEquipment::class,
        ArchiveParticipants::class,
        ArchiveHike::class,
        ArchiveStorage::class,
        ArchiveHikeMealIntakeSheet::class,
        ArchiveHikeProductsParticipants::class,
        ArchiveHikeProductsMealList::class,
        ArchiveHikeListIdProductsInMeal::class,
        ThisHike::class,
        ThisHikeProducts::class,
        ThisHikeEquipment::class,
        ThisHikeParticipants::class,
        ThisHikeMealIntakeSheet::class,
        ThisHikeProductsMealList::class,
        ThisHikeProductsParticipants::class,
        ThisHikeListIdProductsInMeal::class

    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun hikeDao(): HikeDao
}