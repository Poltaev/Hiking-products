package com.example.myapplication.ui.archive_hike_viewing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ArchiveHikeViewingModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllArchiveHike(): List<ArchiveHike> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHike()
    }
}