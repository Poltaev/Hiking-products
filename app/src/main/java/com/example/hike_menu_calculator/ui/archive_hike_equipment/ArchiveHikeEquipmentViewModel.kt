package com.example.hike_menu_calculator.ui.archive_hike_equipment

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase
import kotlinx.coroutines.flow.Flow

class ArchiveHikeEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllArchiveHikeListEquipmentFlow(): Flow<List<ArchiveEquipment>> {
        return ArchiveHikeUseCase(hikeDao).getAllArchiveEquipmentFlow()
    }
    suspend fun getAllArchiveHikeListParticipant(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }

}