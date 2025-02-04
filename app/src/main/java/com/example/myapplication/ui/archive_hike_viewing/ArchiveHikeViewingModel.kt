package com.example.myapplication.ui.archive_hike_viewing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.archive.ArchiveHike
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ArchiveHikeUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ArchiveHikeViewingModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun deleteAcrhiveHike(idArchiveHike: Int) {
        runBlocking(Dispatchers.IO) {
            ArchiveHikeUseCase(hikeDao).getAllListArchiveHike().forEach {
                if (idArchiveHike == it.id) {
                    ArchiveHikeUseCase(hikeDao).deleteArchiveHike(it)
                }
            }
        }
        runBlocking(Dispatchers.IO) {
            ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants().forEach { participant ->
                if (idArchiveHike == participant.hikeId) {
                    ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()
                        .forEach { productsParticipants ->
                            if (participant.id == productsParticipants.participantId) {
                                ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts()
                                    .forEach { product ->
                                        if (productsParticipants.productsId == product.id) {
                                            ArchiveHikeUseCase(hikeDao).deleteArchiveProducts(
                                                product
                                            )
                                        }
                                    }
                                ArchiveHikeUseCase(hikeDao).deleteArchiveHikeProductsParticipants(
                                    productsParticipants
                                )
                            }
                        }
                    ArchiveHikeUseCase(hikeDao).getAllListArchiveEquipment().forEach { equipment ->
                        if (equipment.participantId == participant.id) {
                            ArchiveHikeUseCase(hikeDao).deleteArchiveEquipment(equipment)
                        }
                    }
                    ArchiveHikeUseCase(hikeDao).deleteArchiveParticipants(participant)
                }
            }
        }
        runBlocking(Dispatchers.IO) {
            ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet().forEach {
                if (idArchiveHike == it.hikeId) {
                    ArchiveHikeUseCase(hikeDao).getAllArchiveHikeListIdProductsInMeal()
                        .forEach { idProductMeal ->
                            if (idProductMeal.idMeal == it.id) {
                                ArchiveHikeUseCase(hikeDao).deleteArchiveHikeListIdProductsInMeal(
                                    idProductMeal
                                )
                            }
                        }
                    ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsMealList()
                        .forEach { productsMeal ->
                            if (productsMeal.mealIntakeId == it.id) {
                                ArchiveHikeUseCase(hikeDao).deleteArchiveHikeProductsMealList(
                                    productsMeal
                                )
                            }
                        }
                    ArchiveHikeUseCase(hikeDao).deleteArchiveHikeMealIntakeSheet(it)
                }
            }
        }
    }

    suspend fun getAllArchiveHike(): List<ArchiveHike> {
        return ArchiveHikeUseCase(hikeDao).getAllListArchiveHike()
    }

    suspend fun moveItThisHike(idArchiveHike: Int) {
        val listThisHike = getAllThisHikeList()
        listThisHike.forEach {
            deleteThisHike(it)
        }
        val listThisHikeProducts = getAllThisHikeProductsList()
        listThisHikeProducts.forEach {
            deleteThisHikeProducts(it)
        }
        val listThisHikeEquipment = getAllThisHikeEquipmentList()
        listThisHikeEquipment.forEach {
            deleteThisHikeEquipment(it)
        }
        val listThisHikeParticipants = getAllThisHikeParticipantsList()
        listThisHikeParticipants.forEach {
            deleteThisHikeParticipants(it)
        }
        val listThisHikeMealIntakeSheet = getAllThisHikeMealIntakeSheetList()
        listThisHikeMealIntakeSheet.forEach {
            deleteThisHikeMealIntakeSheet(it)
        }
        val listThisHikeProductsMealList = getAllThisHikeProductsMealListList()
        listThisHikeProductsMealList.forEach {
            deleteThisHikeProductsMealList(it)
        }
        val listThisHikeProductsParticipants = getAllThisHikeProductsParticipantsList()
        listThisHikeProductsParticipants.forEach {
            deleteThisHikeProductsParticipants(it)
        }
        val listIdProduct = ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal()
        listIdProduct.forEach {
            ThisHikeUseCase(hikeDao).deleteThisHikeListIdProductsInMeal(it)
        }
        runBlocking(Dispatchers.IO) {
            ArchiveHikeUseCase(hikeDao).getAllListArchiveHike().forEach { archiveHike ->
                if (archiveHike.id == idArchiveHike) {
                    createHike(
                        archiveHike.name,
                        archiveHike.numberOfDay,
                        archiveHike.numberOfSnacksInDay
                    )
                }
            }
        }
        runBlocking(Dispatchers.IO) {
            createParticipantAndEquipment(idArchiveHike)
        }
        runBlocking(Dispatchers.IO) {
            createMeal(idArchiveHike)
        }
        runBlocking(Dispatchers.IO) {
            createProductAndProductParticipant()
        }
        runBlocking(Dispatchers.IO) {
            createMealProduct()
        }
        runBlocking(Dispatchers.IO) {
            createMealidProduct()
        }
    }

    suspend fun createMealidProduct() {
        ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
            .forEach { thisHikeProductsMeal ->
                ArchiveHikeUseCase(hikeDao).getAllArchiveHikeListIdProductsInMeal()
                    .forEach { mealidProduct ->
                        if (thisHikeProductsMeal.id == mealidProduct.idMeal)
                            ThisHikeUseCase(hikeDao).insertThisHikeListIdProductsInMeal(
                                mealidProduct.id,
                                mealidProduct.idMeal,
                                mealidProduct.idProductsInMeal,
                                mealidProduct.nameProducts
                            )
                    }
            }
    }

    suspend fun createMealProduct() {
        ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsMealList()
            .forEach { mealProductArchive ->
                ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
                    .forEach { mealProductThis ->
                        if (mealProductThis.id == mealProductArchive.mealIntakeId) {
                            ThisHikeUseCase(hikeDao).insertThisHikeProductsMealList(
                                mealProductArchive.mealIntakeId,
                                mealProductArchive.productsId
                            )
                        }
                    }
            }
    }

    suspend fun createProductAndProductParticipant() {
        ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants().forEach { participant ->
            ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()
                .forEach { productsParticipants ->
                    if (participant.id == productsParticipants.participantId) {
                        ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts().forEach { product ->
                            if (productsParticipants.productsId == product.id) {
                                runBlocking {
                                    ThisHikeUseCase(hikeDao).insertThisHikeProducts(
                                        product.id,
                                        product.name,
                                        product.weightForPerson,
                                        product.packageWeight,
                                        product.theWeightOfOneMeal,
                                        product.weightOnTheHike,
                                        product.remainingWeight,
                                        product.partiallyAssembled,
                                        product.fullyAssembled,
                                        product.theVolumeItem,
                                        product.theSoleOwner,
                                        product.nameOwner,
                                        product.idOwner,
                                        product.useTheWholePackInOneMeal,
                                        product.comment
                                    )
                                }
                                ThisHikeUseCase(hikeDao).insertThisHikeProductsParticipants(
                                    participant.id,
                                    product.id
                                )
                            }
                        }
                    }
                }
        }
    }

    suspend fun createMeal(idArchiveHike: Int) {
        ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeMealIntakeSheet().forEach {
            if (it.hikeId == idArchiveHike) {
                ThisHikeUseCase(hikeDao).insertThisHikeMealIntakeSheet(
                    it.id,
                    1,
                    it.name,
                    it.numberOfday,
                    it.typeMeal
                )
            }
        }
    }

    suspend fun createParticipantAndEquipment(idArchiveHike: Int) {
        ArchiveHikeUseCase(hikeDao).getAllListArchiveParticipants().forEach { archiveParticipants ->
            if (archiveParticipants.hikeId == idArchiveHike) {
                ThisHikeUseCase(hikeDao).insertThisHikeParticipants(
                    archiveParticipants.id,
                    1,
                    archiveParticipants.photo,
                    archiveParticipants.firstName,
                    archiveParticipants.lastName,
                    archiveParticipants.gender,
                    archiveParticipants.age,
                    archiveParticipants.maximumPortableWeight,
                    archiveParticipants.weightOfPersonalItems,
                    archiveParticipants.weightWithLoad,
                    archiveParticipants.comment
                )
                ArchiveHikeUseCase(hikeDao).getAllListArchiveEquipment().forEach {
                    if (archiveParticipants.id == it.participantId) {
                        ThisHikeUseCase(hikeDao).insertThisHikeEquipment(
                            it.id,
                            it.participantId,
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
    }

    suspend fun createHike(
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay: Int,
    ) {
        ThisHikeUseCase(hikeDao).insertThisHike(
            id = 1,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
    }

    suspend fun getAllThisHikeList(): List<ThisHike> {
        return ThisHikeUseCase(hikeDao).getAllListThisHike()
    }

    suspend fun deleteThisHike(thisHike: ThisHike) {
        return ThisHikeUseCase(hikeDao).deleteThisHike(thisHike)
    }

    suspend fun getAllThisHikeProductsList(): List<ThisHikeProducts> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
    }

    suspend fun deleteThisHikeProducts(thisHikeProducts: ThisHikeProducts) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeProducts(thisHikeProducts)
    }

    suspend fun getAllThisHikeEquipmentList(): List<ThisHikeEquipment> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeEquipment()
    }

    suspend fun deleteThisHikeEquipment(thisHikeEquipment: ThisHikeEquipment) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeEquipment(thisHikeEquipment)
    }

    suspend fun getAllThisHikeParticipantsList(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }

    suspend fun deleteThisHikeParticipants(thisHikeParticipants: ThisHikeParticipants) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeParticipants(thisHikeParticipants)
    }

    suspend fun getAllThisHikeMealIntakeSheetList(): List<ThisHikeMealIntakeSheet> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
    }

    suspend fun deleteThisHikeMealIntakeSheet(thisHikeMealIntakeSheet: ThisHikeMealIntakeSheet) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeMealIntakeSheet(thisHikeMealIntakeSheet)
    }

    suspend fun getAllThisHikeProductsMealListList(): List<ThisHikeProductsMealList> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsMealList()
    }

    suspend fun deleteThisHikeProductsMealList(thisHikeProductsMealList: ThisHikeProductsMealList) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeProductsMealList(thisHikeProductsMealList)
    }

    suspend fun getAllThisHikeProductsParticipantsList(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }

    suspend fun deleteThisHikeProductsParticipants(thisHikeProductsParticipants: ThisHikeProductsParticipants) {
        return ThisHikeUseCase(hikeDao).deleteThisHikeProductsParticipants(
            thisHikeProductsParticipants
        )
    }
}