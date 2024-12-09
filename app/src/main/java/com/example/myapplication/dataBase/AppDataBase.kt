package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Products::class,
        Hike::class,
        Conjugation::class,
        Participants::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): ProductsDao
}