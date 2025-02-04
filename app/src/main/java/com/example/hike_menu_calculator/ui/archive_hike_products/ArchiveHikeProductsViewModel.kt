package com.example.hike_menu_calculator.ui.archive_hike_products

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase
import kotlinx.coroutines.flow.Flow

class ArchiveHikeProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllArchiveHikeListProductsFlow(): Flow<List<ArchiveProducts>> {
        return ArchiveHikeUseCase(hikeDao).getAllArchiveProductsFlow()
    }
    suspend fun getAllArchiveHikeListParticipant(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }
    suspend fun getAllProductsParticipant(): List<ArchiveHikeProductsParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()
    }

}