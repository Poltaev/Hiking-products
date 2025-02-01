package com.example.myapplication.ui.archive_hike_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveEquipment
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.archive.ArchiveProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
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