package com.example.myapplication.ui.create_hike_participant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CreateHikeParticipantViewModel(private val hikeDao: HikeDao) : ViewModel() {
    val allEquipment = this.hikeDao.getAllEquipmentFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
}