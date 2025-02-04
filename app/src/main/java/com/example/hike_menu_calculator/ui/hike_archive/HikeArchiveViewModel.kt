package com.example.hike_menu_calculator.ui.hike_archive

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase

class HikeArchiveViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllArchiveHike(): List<ArchiveHike> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHike()
    }
}