package com.example.myapplication.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThisHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {

    val allEquipment = this.hikeDao.getAllEquipmentFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )

    fun addEquipment() {
        viewModelScope.launch {
            ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
                    id = 1,
                    name = "name",
                    photo = "photo",
                    weight = 1.2,
                    equipmentInTheCampaign = false
            )
        }
    }
}