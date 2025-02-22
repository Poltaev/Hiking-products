package com.example.hike_menu_calculator.ui.this_hike_list_of_products

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ThisHikeListOfProductsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllProductsFlow(): Flow<List<ThisHikeProducts>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeProductsFlow()
    }

    suspend fun getNameParticipant (listProducts: List<ThisHikeProducts>): List<String> {
        val nameParticipant = mutableListOf<String>()
        val item = ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
        listProducts.forEach { itemProduct ->
            item.forEach { itemProductParticipant ->
                if (itemProduct.id == itemProductParticipant.productsId) {
                    val participantList = ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
                    participantList.forEach { itemParticipant ->
                        if (itemProductParticipant.participantId == itemParticipant.id) {
                            nameParticipant.add(itemParticipant.firstName + " " + itemParticipant.lastName)
                        }
                    }
                }
            }
        }
        return nameParticipant
    }
}