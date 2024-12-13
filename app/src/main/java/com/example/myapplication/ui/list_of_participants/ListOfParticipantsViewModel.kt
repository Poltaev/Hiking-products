package com.example.myapplication.ui.list_of_participants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.Products
import com.example.myapplication.domain.HikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListOfParticipantsViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend  fun getAllParticipantsFlow(): Flow<List<Participants>> {
        return HikeUseCase(hikeDao).getAllCollectionParticipantsFlow()
    }

  suspend  fun getAllParticipantsList(): List<Participants> {
        return HikeUseCase(hikeDao).getAllCollectionParticipantsList()
    }

    suspend fun addParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: Int,
        maximumPortableWeight: Double,
        weightOfPersonalItems: Double,
        participationInTheCampaign: Boolean
    ) {
        HikeUseCase(hikeDao).loadParticipants(
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