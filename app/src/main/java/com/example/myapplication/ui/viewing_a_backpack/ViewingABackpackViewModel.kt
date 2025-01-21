package com.example.myapplication.ui.viewing_a_backpack

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ViewingABackpackViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllProductsParticipantsFlow(): Flow<List<ThisHikeProductsParticipants>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeProductsParticipantsFlow()
    }
    suspend fun getAllListFoodFlow(): Flow<List<ThisHikeProducts>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeProductsFlow()
    }
    suspend fun getAllEquipmentFlow(): Flow<List<ThisHikeEquipment>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow()
    }
    suspend fun getAllParticipantsFlow(): Flow<List<ThisHikeParticipants>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeParticipantsFlow()
    }

}