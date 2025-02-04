package com.example.hike_menu_calculator.ui.view_the_menu

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ViewTheMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuFlow(): Flow<List<ThisHikeMealIntakeSheet>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeMealIntakeSheetFlow()
    }
}