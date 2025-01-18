package com.example.myapplication.ui.create_hike_participant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CreateHikeParticipantViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend  fun getAllParticipantFlow(): Flow<List<Participants>> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsFlow()
    }
    suspend  fun upDateParticipant(
        id: Int,
        photo:String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        participationInTheCampaign: Boolean
    ) {
        return ParticipantsEquipmentUseCase(hikeDao).upDateParticipants(
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
    suspend  fun getAllParticipantsList(): List<Participants> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionParticipantsList()
    }
    suspend fun createHikeParticipant(
        id: Int,
        hikeId: Int,
        photo:String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeParticipants(
            id = id,
            hikeId = hikeId,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            comment = comment
        )
    }
}