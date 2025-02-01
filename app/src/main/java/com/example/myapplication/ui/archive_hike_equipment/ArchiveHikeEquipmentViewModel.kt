package com.example.myapplication.ui.archive_hike_equipment

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveEquipment
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ArchiveHikeEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllArchiveHikeListEquipmentFlow(): Flow<List<ArchiveEquipment>> {
        return ArchiveHikeUseCase(hikeDao).getAllArchiveEquipmentFlow()
    }
    suspend fun getAllArchiveHikeListParticipant(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }

}