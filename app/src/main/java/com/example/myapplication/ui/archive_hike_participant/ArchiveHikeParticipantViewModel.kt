package com.example.myapplication.ui.archive_hike_participant

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class ArchiveHikeParticipantViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllParticipants(): List<ArchiveParticipants> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
    }
    suspend fun upDateParticipant() {
        val listParticipant = ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants()
        listParticipant.forEach {
            var weghtEquipment = 0
            var weghtProduct = 0
            runBlocking {
                ArchiveHikeUseCase(hikeDao).getAllListArchiveEquipment().forEach { equipment ->
                    if (it.id == equipment.participantId) {
                        weghtEquipment += equipment.weight
                    }
                }
            }
            runBlocking {
                ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()
                    .forEach { productsParticipants ->
                        if (it.id == productsParticipants.participantId) {
                            ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts()
                                .forEach { products ->
                                    if (productsParticipants.productsId == products.id) {
                                        weghtProduct += products.remainingWeight
                                    }
                                }
                        }
                    }
            }
            ArchiveHikeUseCase(hikeDao).updateArchiveParticipants(
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