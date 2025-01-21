package com.example.myapplication.ui.pass_on_one_thing

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class PassOnOneThingViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllListFoodFlow(): Flow<List<ThisHikeProducts>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeProductsFlow()
    }
    suspend fun getAllEquipmentFlow(): Flow<List<ThisHikeEquipment>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow()
    }
    suspend fun getAllListParticipantProduct(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }
    suspend fun getAllListParticipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }
}