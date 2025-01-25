package com.example.myapplication.ui.adding_a_participant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AddingAParticipantViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllParticipantsFlow(): Flow<List<Participants>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsFlow()
    }

    suspend fun getAllParticipantsList(): List<Participants> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsList()
    }

    suspend fun deleteParticipants(participants: Participants){
        return ParticipantsEquipmentUseCase(hikeDao).deleteParticipants(participants)
    }
    suspend fun addParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        weightWithLoad: Int,
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
            weightWithLoad  = weightWithLoad,
            participationInTheCampaign = participationInTheCampaign
        )
    }
}