package com.example.hike_menu_calculator.ui.pass_on_one_thing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PassOnOneThingViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getAllEquipment(): List<ThisHikeEquipment> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment()
    }

    suspend fun getAllListParticipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }

    suspend fun getListFood(): List<ThisHikeProducts> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
    }

    suspend fun getProductsParticipants(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }

    fun transferTheEquiopment(
        equipmentId: Int,
        partisipant: String,
        listParticipant: MutableList<String>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val allListParticipant = getAllListParticipant()
            val indexPartisipant = listParticipant.indexOf(partisipant)
            val idParticipant = allListParticipant[indexPartisipant].id
            ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment().forEach {
                if (it.id == equipmentId) {
                    ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants().forEach { item ->
                        if (item.id == it.participantsId) {
                            ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                                item.id,
                                item.hikeId,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                item.weightWithLoad - it.weight,
                                item.comment
                            )
                        }
                        if (item.id == idParticipant) {
                            ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                                item.id,
                                item.hikeId,
                                item.photo,
                                item.firstName,
                                item.lastName,
                                item.gender,
                                item.age,
                                item.maximumPortableWeight,
                                item.weightOfPersonalItems,
                                item.weightWithLoad + it.weight,
                                item.comment
                            )
                        }
                    }
                    ThisHikeUseCase(hikeDao).updateThisHikeEquipment(
                        it.id,
                        idParticipant,
                        it.name,
                        it.photo,
                        it.weight,
                        it.partiallyAssembled,
                        it.fullyAssembled,
                        it.theVolumeItem,
                        it.theSoleOwner,
                        it.nameOwner,
                        it.idOwner,
                        it.comment
                    )
                }
            }
        }
    }

    fun transferTheProduct(
        productId: Int,
        partisipant: String,
        listParticipant: MutableList<String>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val allListParticipant = getAllListParticipant()
            val indexPartisipant = listParticipant.indexOf(partisipant)
            val idParticipant = allListParticipant[indexPartisipant].id
            runBlocking {
                reduceTheWeightOfTheCurrentParticipant(productId)
            }
            ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants().forEach {
                if (it.productsId == productId) {
                    ThisHikeUseCase(hikeDao).deleteThisHikeProductsParticipants(it)
                    ThisHikeUseCase(hikeDao).insertThisHikeProductsParticipants(
                        idParticipant,
                        it.productsId
                    )
                }
                increaseTheParticipantWeight(productId, idParticipant)
            }

        }
    }

    suspend fun reduceTheWeightOfTheCurrentParticipant(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach { item ->
                if (item.id == productId) {
                    ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants().forEach {
                        if (it.productsId == productId) {
                            ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
                                .forEach { item1 ->
                                    if (item1.id == it.participantId) {
                                        ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                                            item1.id,
                                            item1.hikeId,
                                            item1.photo,
                                            item1.firstName,
                                            item1.lastName,
                                            item1.gender,
                                            item1.age,
                                            item1.maximumPortableWeight,
                                            item1.weightOfPersonalItems,
                                            item1.weightWithLoad - item.remainingWeight,
                                            item1.comment
                                        )
                                    }
                                }
                        }
                    }
                }
            }
        }
    }

    fun increaseTheParticipantWeight(productId: Int, idParticipant: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProducts().forEach { item2 ->
                if (item2.id == productId) {
                    ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
                        .forEach { item ->
                            if (item.id == idParticipant) {
                                ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
                                    item.id,
                                    item.hikeId,
                                    item.photo,
                                    item.firstName,
                                    item.lastName,
                                    item.gender,
                                    item.age,
                                    item.maximumPortableWeight,
                                    item.weightOfPersonalItems,
                                    item.weightWithLoad + item2.remainingWeight,
                                    item.comment
                                )
                            }
                        }
                }
            }
        }
    }
}