package com.example.myapplication.dataBase

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class App : Application() {

     lateinit var db : AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).build()
    }
}