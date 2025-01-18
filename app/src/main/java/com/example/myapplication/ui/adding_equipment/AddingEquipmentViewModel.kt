package com.example.myapplication.ui.adding_equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddingEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {


    suspend fun getAllEquipmentList(): List<Equipment> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
    }

    suspend fun deleteEquipment(equipment: Equipment){
        return ParticipantsEquipmentUseCase(hikeDao).deleteEquipment(equipment)
    }
    suspend fun addEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean

    ) {
        ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign

        )
    }
}