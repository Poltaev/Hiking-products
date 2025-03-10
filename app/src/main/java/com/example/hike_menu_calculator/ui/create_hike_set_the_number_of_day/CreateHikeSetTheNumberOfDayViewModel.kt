package com.example.hike_menu_calculator.ui.create_hike_set_the_number_of_day

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHike
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.hike_menu_calculator.domain.ThisHikeUseCase

class CreateHikeSetTheNumberOfDayViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun createHike(
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay:Int
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHike(
            id = 1,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
    }

    suspend fun deleteALL() {
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