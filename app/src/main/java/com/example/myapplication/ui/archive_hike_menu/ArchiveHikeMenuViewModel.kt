package com.example.myapplication.ui.archive_hike_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ArchiveHikeMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuFlow():List<ArchiveHikeMealIntakeSheet> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet()
    }
}