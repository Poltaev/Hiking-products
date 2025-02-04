package com.example.hike_menu_calculator.ui.archive_hike_participant

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase
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