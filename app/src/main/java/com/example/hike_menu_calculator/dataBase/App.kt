package com.example.hike_menu_calculator.dataBase

import android.app.Application
import androidx.room.Room

class App : Application() {

     lateinit var db : AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.inMemoryDatabaseBuilder(
            applicationContext,
            AppDataBase::class.java
        ).build()
    }
}