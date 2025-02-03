package com.example.myapplication.ui.this_hike_list_of_participants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class ThisHikeListOfParticipantsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllParticipants(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }

    suspend fun upDateParticipant() {
        val listParticipant = ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
        listParticipant.forEach {
            var weghtEquipment = 0
            var weghtProduct = 0
            runBlocking {
                ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment().forEach { equipment ->
                    if (it.id == equipment.participantsId) {
                        weghtEquipment += equipment.weight
                    }
                }
            }
            runBlocking {
                ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
                    .forEach { productsParticipants ->
                        if (it.id == productsParticipants.participantId) {
                            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
                                .forEach { products ->
                                    if (productsParticipants.productsId == products.id) {
                                        weghtProduct += products.remainingWeight
                                    }
                                }
                        }
                    }
            }
            ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                it.id,
                it.hikeId,
                it.photo,
                it.firstName,
                it.lastName,
                it.gender,
                it.age,
                it.maximumPortableWeight,
                it.weightOfPersonalItems,
                it.weightOfPersonalItems + weghtEquipment + weghtProduct,
                it.comment
            )
        }
    }
}