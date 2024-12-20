package com.example.myapplication.ui.list_of_participants

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import kotlinx.coroutines.flow.Flow

class ListOfParticipantsViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend  fun getAllParticipantsFlow(): Flow<List<Participants>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsFlow()
    }

  suspend  fun getAllParticipantsList(): List<Participants> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsList()
    }

    suspend fun addParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Double,
        weightOfPersonalItems: Double,
        participationInTheCampaign: Boolean
    ) {
        ParticipantsEquipmentUseCase(hikeDao).loadParticipants(
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
    }
}