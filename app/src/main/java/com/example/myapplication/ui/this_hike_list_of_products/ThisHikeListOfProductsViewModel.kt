package com.example.myapplication.ui.this_hike_list_of_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ThisHikeListOfProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllProductsFlow(): Flow<List<ThisHikeProducts>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeProductsFlow()
    }
    suspend fun getAllPartisipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }
    suspend fun getAllProductsParticipant(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }

}