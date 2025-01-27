package com.example.myapplication.ui.pass_on_one_thing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PassOnOneThingViewModel(private val hikeDao: HikeDao) : ViewModel() {

    fun getAllEquipment(): List<ThisHikeEquipment> {
        var listEquipment = listOf<ThisHikeEquipment>()
        viewModelScope.launch(Dispatchers.IO) {
            listEquipment = ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment()

        }
        return listEquipment
    }

    fun getAllListParticipant(): List<ThisHikeParticipants> {
        var listParticipants = listOf<ThisHikeParticipants>()
        viewModelScope.launch(Dispatchers.IO) {
            listParticipants = ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()

        }
        return listParticipants
    }

    fun getListFood(): List<ThisHikeProducts> {
        var listProducts = listOf<ThisHikeProducts>()
        viewModelScope.launch(Dispatchers.IO) {
            listProducts = ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
        }
        return listProducts
    }

    fun getListIdParticipant(productId: Int): MutableList<Int> {
        val listIdParticipant = mutableListOf<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants().forEach {
                if (it.productsId == productId) {
                    listIdParticipant.add(it.participantId)
                }
            }
        }
        return listIdParticipant
    }

    fun transferTheEquiopment(
        equipmentId: Int,
        partisipant: String,
        listParticipant: MutableList<String>,
    ) {
        val allListParticipant = getAllListParticipant()
        val indexPartisipant = listParticipant.indexOf(partisipant)
        val idParticipant = allListParticipant[indexPartisipant].id
        viewModelScope.launch(Dispatchers.IO) {
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
        val allListParticipant = getAllListParticipant()
        val indexPartisipant = listParticipant.indexOf(partisipant)
        val idParticipant = allListParticipant[indexPartisipant].id
        reduceTheWeightOfTheCurrentParticipant(productId)
        viewModelScope.launch(Dispatchers.IO) {
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

    fun reduceTheWeightOfTheCurrentParticipant(productId: Int) {
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

    fun increaseTheParticipantWeight(productId: Int, idParticipant:Int) {
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