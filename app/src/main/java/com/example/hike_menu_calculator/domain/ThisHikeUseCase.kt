package com.example.hike_menu_calculator.domain

import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHike
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeParticipants
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProductsParticipants
import kotlinx.coroutines.flow.Flow

class ThisHikeUseCase(private val hikeDao: HikeDao) {
    // ThisHikeListIdProductsInMeal
    suspend fun getAllThisHikeListIdProductsInMealFlow(): Flow<List<ThisHikeListIdProductsInMeal>> {
        return hikeDao.getAllThisHikeListIdProductsInMealFlow()
    }

    suspend fun getAllThisHikeListIdProductsInMeal(): List<ThisHikeListIdProductsInMeal> {
        return hikeDao.getAllThisHikeListIdProductsInMeal()
    }

    suspend fun insertThisHikeListIdProductsInMeal(
        id: Int,
        idMeal: Int,
        idProductsInMeal: Int,
        nameProducts: String
    ) {
        val thisHikeListIdProductsInMeal = ThisHikeListIdProductsInMeal(
            id = id,
            idMeal = idMeal,
            idProductsInMeal = idProductsInMeal,
            nameProducts = nameProducts
        )
        hikeDao.insertThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal)
    }

    suspend fun deleteThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal: ThisHikeListIdProductsInMeal) {
        hikeDao.deleteThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal)
    }

    suspend fun updateThisHikeListIdProductsInMeal(
        id: Int,
        idMeal: Int,
        idProductsInMeal: Int,
        nameProducts: String
    ) {
        val thisHikeListIdProductsInMeal = ThisHikeListIdProductsInMeal(
            id = id,
            idMeal = idMeal,
            idProductsInMeal = idProductsInMeal,
            nameProducts = nameProducts
        )
        hikeDao.updateThisHikeListIdProductsInMeal(thisHikeListIdProductsInMeal)
    }

    // ThisHike
    suspend fun getAllThisHikeFlow(): Flow<List<ThisHike>> {
        return hikeDao.getAllThisHikeFlow()
    }

    suspend fun getAllListThisHike(): List<ThisHike> {
        return hikeDao.getAllListThisHike()
    }

    suspend fun insertThisHike(
        id: Int,
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay: Int,
    ) {
        val thisHike = ThisHike(
            id = id,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
        hikeDao.insertThisHike(thisHike)
    }

    suspend fun deleteThisHike(thisHike: ThisHike) {
        hikeDao.deleteThisHike(thisHike)
    }

    suspend fun updateThisHike(
        id: Int,
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay: Int,
    ) {
        val thisHike = ThisHike(
            id = id,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
        hikeDao.updateThisHike(thisHike)
    }
    // ThisHikeProducts

    suspend fun getAllThisHikeProductsFlow(): Flow<List<ThisHikeProducts>> {
        return hikeDao.getAllThisHikeProductsFlow()
    }

    suspend fun getAllListThisHikeProducts(): List<ThisHikeProducts> {
        return hikeDao.getAllListThisHikeProducts()
    }

    suspend fun insertThisHikeProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        comment: String,
    ) {
        val thisHikeProducts = ThisHikeProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theWeightOfOneMeal = theWeightOfOneMeal,
            weightOnTheHike = weightOnTheHike,
            remainingWeight = remainingWeight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            comment = comment
        )
        hikeDao.insertThisHikeProducts(thisHikeProducts)
    }

    suspend fun deleteThisHikeProducts(thisHikeProducts: ThisHikeProducts) {
        hikeDao.deleteThisHikeProducts(thisHikeProducts)
    }

    suspend fun updateThisHikeProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        comment: String,
    ) {
        val thisHikeProducts = ThisHikeProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theWeightOfOneMeal = theWeightOfOneMeal,
            weightOnTheHike = weightOnTheHike,
            remainingWeight = remainingWeight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            comment = comment
        )
        hikeDao.updateThisHikeProducts(thisHikeProducts)
    }

    // ThisHikeEquipment
    suspend fun getAllThisHikeEquipmentFlow(): Flow<List<ThisHikeEquipment>> {
        return hikeDao.getAllThisHikeEquipmentFlow()
    }

    suspend fun getAllListThisHikeEquipment(): List<ThisHikeEquipment> {
        return hikeDao.getAllListThisHikeEquipment()
    }

    suspend fun insertThisHikeEquipment(
        id: Int,
        participantsId: Int,
        name: String,
        photo: String,
        weight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        comment: String,
    ) {
        val thisHikeEquipment = ThisHikeEquipment(
            id = id,
            participantsId = participantsId,
            name = name,
            photo = photo,
            weight = weight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            comment = comment
        )
        hikeDao.insertThisHikeEquipment(thisHikeEquipment)
    }

    suspend fun deleteThisHikeEquipment(thisHikeEquipment: ThisHikeEquipment) {
        hikeDao.deleteThisHikeEquipment(thisHikeEquipment)
    }

    suspend fun updateThisHikeEquipment(
        id: Int,
        participantsId: Int,
        name: String,
        photo: String,
        weight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        comment: String,
    ) {
        val thisHikeEquipment = ThisHikeEquipment(
            id = id,
            participantsId = participantsId,
            name = name,
            photo = photo,
            weight = weight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            comment = comment
        )
        hikeDao.updateThisHikeEquipment(thisHikeEquipment)
    }

    // ThisHikeParticipants
    suspend fun getAllThisHikeParticipantsFlow(): Flow<List<ThisHikeParticipants>> {
        return hikeDao.getAllThisHikeParticipantsFlow()
    }

    suspend fun getAllListThisHikeParticipants(): List<ThisHikeParticipants> {
        return hikeDao.getAllListThisHikeParticipants()
    }

    suspend fun insertThisHikeParticipants(
        id: Int,
        hikeId: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        weightWithLoad: Int,
        comment: String,
    ) {
        val thisHikeParticipants = ThisHikeParticipants(
            id = id,
            hikeId = hikeId,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            weightWithLoad = weightWithLoad,
            comment = comment
        )
        hikeDao.insertThisHikeParticipants(thisHikeParticipants)
    }

    suspend fun deleteThisHikeParticipants(thisHikeParticipants: ThisHikeParticipants) {
        hikeDao.deleteThisHikeParticipants(thisHikeParticipants)
    }

    suspend fun updateThisHikeParticipants(
        id: Int,
        hikeId: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: String,
        maximumPortableWeight: Int,
        weightOfPersonalItems: Int,
        weightWithLoad: Int,
        comment: String,
    ) {
        val thisHikeParticipants = ThisHikeParticipants(
            id = id,
            hikeId = hikeId,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            weightWithLoad = weightWithLoad,
            comment = comment
        )
        hikeDao.updateThisHikeParticipants(thisHikeParticipants)
    }

    // ThisHikeMealIntakeSheet
    suspend fun getAllThisHikeMealIntakeSheetFlow(): Flow<List<ThisHikeMealIntakeSheet>> {
        return hikeDao.getAllThisHikeMealIntakeSheetFlow()
    }

    suspend fun getAllListThisHikeMealIntakeSheet(): List<ThisHikeMealIntakeSheet> {
        return hikeDao.getAllListThisHikeMealIntakeSheet()
    }

    suspend fun insertThisHikeMealIntakeSheet(
        id: Int,
        hikeId: Int,
        name: String,
        numberOfday: Int,
        typeMeal: String
    ) {
        val thisHikeMealIntakeSheet = ThisHikeMealIntakeSheet(
            id = id,
            hikeId = hikeId,
            name = name,
            numberOfday = numberOfday,
            typeMeal = typeMeal
        )
        hikeDao.insertThisHikeMealIntakeSheet(thisHikeMealIntakeSheet)
    }

    suspend fun deleteThisHikeMealIntakeSheet(thisHikeMealIntakeSheet: ThisHikeMealIntakeSheet) {
        hikeDao.deleteThisHikeMealIntakeSheet(thisHikeMealIntakeSheet)
    }

    suspend fun updateThisHikeMealIntakeSheet(
        id: Int,
        hikeId: Int,
        name: String,
        numberOfday: Int,
        typeMeal: String,
    ) {
        val thisHikeMealIntakeSheet = ThisHikeMealIntakeSheet(
            id = id,
            hikeId = hikeId,
            name = name,
            numberOfday = numberOfday,
            typeMeal = typeMeal
        )
        hikeDao.updateThisHikeMealIntakeSheet(thisHikeMealIntakeSheet)
    }
    // ThisHikeProductsMealList

    suspend fun getAllThisHikeProductsMealListFlow(): Flow<List<ThisHikeProductsMealList>> {
        return hikeDao.getAllThisHikeProductsMealListFlow()
    }

    suspend fun getAllListThisHikeProductsMealList(): List<ThisHikeProductsMealList> {
        return hikeDao.getAllListThisHikeProductsMealList()
    }

    suspend fun insertThisHikeProductsMealList(
        mealIntakeId: Int,
        productsId: Int,
    ) {
        val thisHikeProductsMealList = ThisHikeProductsMealList(
            mealIntakeId = mealIntakeId,
            productsId = productsId
        )
        hikeDao.insertThisHikeProductsMealList(thisHikeProductsMealList)
    }

    suspend fun deleteThisHikeProductsMealList(thisHikeProductsMealList: ThisHikeProductsMealList) {
        hikeDao.deleteThisHikeProductsMealList(thisHikeProductsMealList)
    }

    suspend fun updateThisHikeProductsMealList(
        mealIntakeId: Int,
        productsId: Int,
    ) {
        val thisHikeProductsMealList = ThisHikeProductsMealList(
            mealIntakeId = mealIntakeId,
            productsId = productsId
        )
        hikeDao.updateThisHikeProductsMealList(thisHikeProductsMealList)
    }

    // ThisHikeProductsParticipants
    suspend fun getAllThisHikeProductsParticipantsFlow(): Flow<List<ThisHikeProductsParticipants>> {
        return hikeDao.getAllThisHikeProductsParticipantsFlow()
    }

    suspend fun getAllListThisHikeProductsParticipants(): List<ThisHikeProductsParticipants> {
        return hikeDao.getAllListThisHikeProductsParticipants()
    }

    suspend fun insertThisHikeProductsParticipants(
        participantId: Int,
        productsId: Int,
    ) {
        val thisHikeProductsParticipants = ThisHikeProductsParticipants(
            participantId = participantId,
            productsId = productsId
        )
        hikeDao.insertThisHikeProductsParticipants(thisHikeProductsParticipants)
    }

    suspend fun deleteThisHikeProductsParticipants(thisHikeProductsParticipants: ThisHikeProductsParticipants) {
        hikeDao.deleteThisHikeProductsParticipants(thisHikeProductsParticipants)
    }

    suspend fun updateThisHikeProductsParticipants(
        participantId: Int,
        productsId: Int,
    ) {
        val thisHikeProductsParticipants = ThisHikeProductsParticipants(
            participantId = participantId,
            productsId = productsId
        )
        hikeDao.updateThisHikeProductsParticipants(thisHikeProductsParticipants)
    }

}