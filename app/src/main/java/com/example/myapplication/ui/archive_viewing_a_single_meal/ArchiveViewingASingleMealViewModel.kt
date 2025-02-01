package com.example.myapplication.ui.archive_viewing_a_single_meal

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.myapplication.dataBase.archive.ArchiveHikeProductsMealList
import com.example.myapplication.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.archive.ArchiveProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase

class ArchiveViewingASingleMealViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuList():List<ArchiveHikeProductsMealList> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsMealList()
    }
    suspend fun getAllListFood(): List<ArchiveProducts> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts()
    }
    suspend fun getAllPartisipant(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }
    suspend fun getAllProductsParticipant(): List<ArchiveHikeProductsParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()
    }
}