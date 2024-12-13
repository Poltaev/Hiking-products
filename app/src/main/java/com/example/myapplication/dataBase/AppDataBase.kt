package com.example.myapplication.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Products::class,
        CurrentHike::class,
        Equipment::class,
        Participants::class,
        ArchiveHike::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun hikeDao(): HikeDao
}