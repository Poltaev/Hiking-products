package com.example.hike_menu_calculator.ui.adding_equipment

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.domain.ParticipantsEquipmentUseCase

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
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        equipmentInTheCampaign: Boolean

    ) {
        ParticipantsEquipmentUseCase(hikeDao).loadEquipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            equipmentInTheCampaign = equipmentInTheCampaign

        )
    }
}