package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dataBase.archive.ArchiveEquipment
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.myapplication.dataBase.archive.ArchiveHikeProductsMealList
import com.example.myapplication.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.archive.ArchiveProducts
import com.example.myapplication.dataBase.archive.ArchiveStorage
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.ProductStorage
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants

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
        ThisHike::class,
        ThisHikeProducts::class,
        ThisHikeEquipment::class,
        ThisHikeParticipants::class,
        ThisHikeMealIntakeSheet::class,
        ThisHikeProductsMealList::class,
        ThisHikeProductsParticipants::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun hikeDao(): HikeDao
}