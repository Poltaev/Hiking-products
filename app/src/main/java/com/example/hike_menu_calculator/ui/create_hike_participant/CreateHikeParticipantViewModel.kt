package com.example.hike_menu_calculator.ui.create_hike_participant

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.Participants
import com.example.hike_menu_calculator.domain.ParticipantsEquipmentUseCase
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

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
        weightWithLoad: Int,
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
            weightWithLoad =weightWithLoad,
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
        weightWithLoad: Int,
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
            weightWithLoad = weightWithLoad,
            comment = comment
        )
    }
}