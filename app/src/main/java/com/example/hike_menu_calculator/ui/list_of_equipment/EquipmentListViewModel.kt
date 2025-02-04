package com.example.hike_menu_calculator.ui.list_of_equipment

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.domain.ParticipantsEquipmentUseCase
import kotlinx.coroutines.flow.Flow

class EquipmentListViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend  fun getAllEquipmentFlow(): Flow<List<Equipment>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentFlow()
    }


}