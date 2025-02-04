package com.example.hike_menu_calculator.ui.archive_viewing_a_single_meal

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase

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