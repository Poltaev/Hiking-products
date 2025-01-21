package com.example.myapplication.ui.view_the_menu

import android.health.connect.datatypes.MealType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewTheMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuFlow(): Flow<List<ThisHikeMealIntakeSheet>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeMealIntakeSheetFlow()
    }
}