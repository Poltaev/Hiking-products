package com.example.myapplication.domain

import androidx.room.ColumnInfo
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import kotlinx.coroutines.flow.Flow

class ParticipantsEquipmentUseCase(private val hikeDao: HikeDao) {
// Equipment

    suspend fun getAllCollectionEquipmentFlow(): Flow<List<Equipment>> {
        return hikeDao.getAllEquipmentFlow()
    }

    suspend  fun getAllCollectionEquipmentList(): List<Equipment> {
        return hikeDao.getAllListEquipment()
    }

    suspend fun loadEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean
    ) {
        val equipmentForLoad = Equipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign
        )
        hikeDao.insertOneEquipment(equipmentForLoad)
    }

    suspend fun deleteEquipment(equipment: Equipment) {
        hikeDao.deleteOneEquipment(equipment)
    }

    suspend fun upDateEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean
    ) {
        val equipment = Equipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem =theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign
        )
        hikeDao.updateOneEquipment(equipment)
    }

    // Participants
    suspend  fun getAllCollectionParticipantsFlow(): Flow<List<Participants>> {
        return hikeDao.getAllParticipantsFlow()
    }

    suspend fun getAllCollectionParticipantsList(): List<Participants> {
        return hikeDao.getAllListParticipants()
    }

    suspend fun loadParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        participationInTheCampaign: Boolean
    ) {
        val participantsForLoad = Participants(
            id = id,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            participationInTheCampaign = participationInTheCampaign
        )
        hikeDao.insertOneParticipant(participantsForLoad)
    }

    suspend fun deleteParticipants(participants: Participants) {
        hikeDao.deleteOneParticipant(participants)
    }

    suspend fun upDateParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        participationInTheCampaign: Boolean
    ) {
        val participants = Participants(
            id = id,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            participationInTheCampaign = participationInTheCampaign
        )
        hikeDao.updateOneParticipant(participants)
    }
}