package com.example.myapplication.ui.create_hike_equipment

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class CreateHikeEquipmentViewModel(private val hikeDao: HikeDao)  : ViewModel() {
    suspend  fun getAllEquipmentFlow(): Flow<List<Equipment>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentFlow()
    }
    suspend  fun upDateEquipment(
        id: Int,
        name:String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean
    ) {
        return ParticipantsEquipmentUseCase(hikeDao).upDateEquipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign
        )
    }
    suspend  fun getAllEquipmentList(): List<Equipment> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
    }
    suspend fun createHikeEquipment(
        id: Int,
        hikeId: Int,
        name:String,
        photo: String,
        weight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeEquipment(
            id = id,
            hikeId = hikeId,
            name = name,
            photo = photo,
            weight = weight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            comment = comment
        )
    }

}