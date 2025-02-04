package com.example.hike_menu_calculator.ui.this_hike_list_of_equipment

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ThisHikeListOfEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllThisHikeListEquipmentFlow(): Flow<List<ThisHikeEquipment>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow()
    }
    suspend fun getAllThisHikeListParticipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }

}