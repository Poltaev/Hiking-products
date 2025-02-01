package com.example.myapplication.ui.hike_archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HikeArchiveViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllArchiveHike(): List<ArchiveHike> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHike()
    }
}