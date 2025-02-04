package com.example.hike_menu_calculator.ui.create_hike_equipment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.Equipment
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.domain.ParticipantsEquipmentUseCase
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CreateHikeEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllEquipmentFlow(): Flow<List<Equipment>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentFlow()
    }

    suspend fun upDateEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        equipmentInTheCampaign: Boolean,
    ) {
        return ParticipantsEquipmentUseCase(hikeDao).upDateEquipment(
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

    suspend fun getAllEquipmentList(): List<Equipment> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
    }

    fun addEquipmentToBackpack() {
        viewModelScope.launch(Dispatchers.IO) {
            ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList().forEach {
                if (it.equipmentInTheCampaign) {
                    val listParticipant = ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
                    val listParticipantMaxWeight = mutableListOf<Double>()
                    val listParticipantWeight = mutableListOf<Double>()
                    listParticipant.forEach {
                        listParticipantMaxWeight.add(it.maximumPortableWeight.toDouble())
                        listParticipantWeight.add(it.weightWithLoad.toDouble())
                    }
                    val theLightestBackpackPossible = mutableListOf<Double>()
                    for (i in 0..listParticipant.size - 1) {
                        val sum =
                            ((listParticipantMaxWeight[i] - listParticipantWeight[i]) / listParticipantMaxWeight[i]) * 100
                        theLightestBackpackPossible.add(sum)
                    }
                    val theLightestBackpackPossibleSort = theLightestBackpackPossible.sortedDescending()
                    val returnIndexPartisipant = theLightestBackpackPossible.indexOf(theLightestBackpackPossibleSort[0])
                    ThisHikeUseCase(hikeDao).insertThisHikeEquipment(
                        it.id,
                        listParticipant[returnIndexPartisipant].id,
                        it.name,
                        it.photo,
                        it.weight,
                        false,
                        false,
                        false,
                        false,
                        "nameAll",
                        0,
                        ""
                    )
                    ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                        listParticipant[returnIndexPartisipant].id,
                        listParticipant[returnIndexPartisipant].hikeId,
                        listParticipant[returnIndexPartisipant].photo,
                        listParticipant[returnIndexPartisipant].firstName,
                        listParticipant[returnIndexPartisipant].lastName,
                        listParticipant[returnIndexPartisipant].gender,
                        listParticipant[returnIndexPartisipant].age,
                        listParticipant[returnIndexPartisipant].maximumPortableWeight,
                        listParticipant[returnIndexPartisipant].weightOfPersonalItems,
                        listParticipant[returnIndexPartisipant].weightWithLoad + it.weight,
                        listParticipant[returnIndexPartisipant].comment
                    )
                }
            }
        }
    }
}