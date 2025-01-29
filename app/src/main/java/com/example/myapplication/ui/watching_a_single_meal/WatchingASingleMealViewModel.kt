package com.example.myapplication.ui.watching_a_single_meal

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class WatchingASingleMealViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllMenuList(): List<ThisHikeProductsMealList> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsMealList()
    }
    suspend fun getAllListFood(): List<ThisHikeProducts> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
    }
    suspend fun getAllPartisipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }
    suspend fun getAllProductsParticipant(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }
}