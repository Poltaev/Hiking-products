package com.example.hike_menu_calculator.ui.archive_hike_menu

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase

class ArchiveHikeMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuFlow():List<ArchiveHikeMealIntakeSheet> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet()
    }
}