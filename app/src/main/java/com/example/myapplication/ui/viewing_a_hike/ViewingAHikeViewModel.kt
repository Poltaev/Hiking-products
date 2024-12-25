package com.example.myapplication.ui.viewing_a_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewingAHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {
    val allEquipment = this.hikeDao.getAllEquipmentFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
}