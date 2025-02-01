package com.example.myapplication.ui.archive_hike_participant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ArchiveHikeParticipantViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllParticipants(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }
}