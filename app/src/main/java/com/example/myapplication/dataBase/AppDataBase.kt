package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.ProductStorage
import com.example.myapplication.dataBase.products.Products

@Database(
    entities = [
        Products::class,
        CurrentHike::class,
        Equipment::class,
        Participants::class,
        ArchiveHike::class,
        ProductStorage::class,
        ListTypeOfProducts::class,
        ListProducts::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun hikeDao(): HikeDao
}