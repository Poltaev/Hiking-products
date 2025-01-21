package com.example.myapplication.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThisHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllThisHike(): List<ThisHike> {
        return ThisHikeUseCase(hikeDao).getAllListThisHike()
    }
}