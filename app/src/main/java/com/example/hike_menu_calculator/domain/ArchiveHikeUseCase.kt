package com.example.hike_menu_calculator.domain

import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHike
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeListIdProductsInMeal
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeMealIntakeSheet
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsMealList
import com.example.hike_menu_calculator.dataBase.archive.ArchiveHikeProductsParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveParticipants
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.dataBase.archive.ArchiveStorage
import kotlinx.coroutines.flow.Flow

class ArchiveHikeUseCase(private val hikeDao: HikeDao) {
    // ArchiveHikeListIdProductsInMeal
    suspend fun getAllArchiveHikeListIdProductsInMealFlow(): Flow<List<ArchiveHikeListIdProductsInMeal>> {
        return hikeDao.getAllArchiveHikeListIdProductsInMealFlow()
    }

    suspend fun getAllArchiveHikeListIdProductsInMeal(): List<ArchiveHikeListIdProductsInMeal> {
        return hikeDao.getAllArchiveHikeListIdProductsInMeal()
    }

    suspend fun insertArchiveHikeListIdProductsInMeal(
        id: Int,
        idMeal: Int,
        idProductsInMeal: Int,
        nameProducts: String
    ) {
        val archiveHikeListIdProductsInMeal = ArchiveHikeListIdProductsInMeal(
            id = id,
            idMeal = idMeal,
            idProductsInMeal = idProductsInMeal,
            nameProducts = nameProducts
        )
        hikeDao.insertArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal)
    }

    suspend fun deleteArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal: ArchiveHikeListIdProductsInMeal) {
        hikeDao.deleteArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal)
    }

    suspend fun updateArchiveHikeListIdProductsInMeal(
        id: Int,
        idMeal: Int,
        idProductsInMeal: Int,
        nameProducts: String
    ) {
        val archiveHikeListIdProductsInMeal = ArchiveHikeListIdProductsInMeal(
            id = id,
            idMeal = idMeal,
            idProductsInMeal = idProductsInMeal,
            nameProducts = nameProducts
        )
        hikeDao.updateArchiveHikeListIdProductsInMeal(archiveHikeListIdProductsInMeal)
    }
    // ArchiveProducts
    suspend fun getAllArchiveProductsFlow(): Flow<List<ArchiveProducts>> {
        return hikeDao.getAllArchiveProductsFlow()
    }

    suspend fun getAllListArchiveProducts(): List<ArchiveProducts> {
        return hikeDao.getAllListArchiveProducts()
    }

    suspend fun insertArchiveProducts(
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
        comment: String
    ) {
        val archiveProducts = ArchiveProducts(
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
        hikeDao.insertArchiveProducts(archiveProducts)
    }

    suspend fun deleteArchiveProducts(archiveProducts: ArchiveProducts) {
        hikeDao.deleteArchiveProducts(archiveProducts)
    }

    suspend fun updateOneArchiveProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        theVolumeItem: Boolean,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        comment: String,
    ) {
        val archiveProducts = ArchiveProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theWeightOfOneMeal = theWeightOfOneMeal,
            weightOnTheHike = weightOnTheHike,
            remainingWeight = remainingWeight,
            theVolumeItem = theVolumeItem,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            comment = comment
        )
        hikeDao.updateOneArchiveProducts(archiveProducts)
    }
    // ArchiveEquipment

    suspend fun getAllArchiveEquipmentFlow(): Flow<List<ArchiveEquipment>> {
        return hikeDao.getAllArchiveEquipmentFlow()
    }

    suspend fun getAllListArchiveEquipment(): List<ArchiveEquipment> {
        return hikeDao.getAllListArchiveEquipment()
    }

    suspend fun insertArchiveEquipment(
        id: Int,
        participantId: Int,
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
        val archiveEquipment = ArchiveEquipment(
            id = id,
            participantId = participantId,
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
        hikeDao.insertArchiveEquipment(archiveEquipment)
    }

    suspend fun deleteArchiveEquipment(archiveEquipment: ArchiveEquipment) {
        hikeDao.deleteArchiveEquipment(archiveEquipment)
    }

    suspend fun updateArchiveEquipment(
        id: Int,
        participantId: Int,
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
        val archiveEquipment = ArchiveEquipment(
            id = id,
            participantId = participantId,
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
        hikeDao.updateArchiveEquipment(archiveEquipment)
    }

    // ArchiveParticipants
    suspend fun getAllArchiveParticipantsFlow(): Flow<List<ArchiveParticipants>> {
        return hikeDao.getAllArchiveParticipantsFlow()
    }

    suspend fun getAllListArchiveParticipants(): List<ArchiveParticipants> {
        return hikeDao.getAllListArchiveParticipants()
    }

    suspend fun insertArchiveParticipants(
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
        val archiveParticipants = ArchiveParticipants(
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
            comment = comment,

            )
        hikeDao.insertArchiveParticipants(archiveParticipants)
    }

    suspend fun deleteArchiveParticipants(archiveParticipants: ArchiveParticipants) {
        hikeDao.deleteArchiveParticipants(archiveParticipants)
    }

    suspend fun updateArchiveParticipants(
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
        val archiveParticipants = ArchiveParticipants(
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
            comment = comment,

            )
        hikeDao.updateArchiveParticipants(archiveParticipants)
    }

    // ArchiveHike
    suspend fun getAllArchiveHikeFlow(): Flow<List<ArchiveHike>> {
        return hikeDao.getAllArchiveHikeFlow()
    }

    suspend fun getAllListArchiveHike(): List<ArchiveHike> {
        return hikeDao.getAllListArchiveHike()
    }

    suspend fun insertArchiveHike(
        id: Int,
        storageId: Int,
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay: Int,
    ) {
        val archiveHike = ArchiveHike(
            id = id,
            storageId = storageId,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
        hikeDao.insertArchiveHike(archiveHike)
    }

    suspend fun deleteArchiveHike(archiveHike: ArchiveHike) {
        hikeDao.deleteArchiveHike(archiveHike)
    }

    suspend fun updateArchiveHike(
        id: Int,
        storageId: Int,
        name: String,
        numberOfDay: Int,
        numberOfSnacksInDay: Int,
    ) {
        val archiveHike = ArchiveHike(
            id = id,
            storageId = storageId,
            name = name,
            numberOfDay = numberOfDay,
            numberOfSnacksInDay = numberOfSnacksInDay
        )
        hikeDao.updateArchiveHike(archiveHike)
    }

    // ArchiveStorage
    suspend fun getAllArchiveStorageFlow(): Flow<List<ArchiveStorage>> {
        return hikeDao.getAllArchiveStorageFlow()
    }

    suspend fun getAllListArchiveStorage(): List<ArchiveStorage> {
        return hikeDao.getAllListArchiveStorage()
    }

    suspend fun insertArchiveStorage(
        id: Int,
        name: String,
    ) {
        val archiveStorage = ArchiveStorage(
            id = id,
            name = name
        )
        hikeDao.insertArchiveStorage(archiveStorage)
    }

    suspend fun deleteArchiveStorage(archiveStorage: ArchiveStorage) {
        hikeDao.deleteArchiveStorage(archiveStorage)
    }

    suspend fun updateArchiveStorage(
        id: Int,
        name: String,
    ) {
        val archiveStorage = ArchiveStorage(
            id = id,
            name = name
        )
        hikeDao.updateArchiveStorage(archiveStorage)
    }
    // ArchiveHikeMealIntakeSheet

    suspend fun getAllArchiveHikeMealIntakeSheetFlow(): Flow<List<ArchiveHikeMealIntakeSheet>> {
        return hikeDao.getAllArchiveHikeMealIntakeSheetFlow()
    }

    suspend fun getAllListArchiveHikeMealIntakeSheet(): List<ArchiveHikeMealIntakeSheet> {
        return hikeDao.getAllListArchiveHikeMealIntakeSheet()
    }

    suspend fun insertArchiveHikeMealIntakeSheet(
        id: Int,
        hikeId: Int,
        name: String,
        numberOfday: Int,
        typeMeal: String
    ) {
        val archiveHikeMealIntakeSheet = ArchiveHikeMealIntakeSheet(
            id = id,
            hikeId = hikeId,
            name = name,
            numberOfday = numberOfday,
            typeMeal = typeMeal
        )
        hikeDao.insertArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet)
    }

    suspend fun deleteArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet: ArchiveHikeMealIntakeSheet) {
        hikeDao.deleteArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet)
    }

    suspend fun updateArchiveHikeMealIntakeSheet(
        id: Int,
        hikeId: Int,
        name: String,
        numberOfday: Int,
        typeMeal: String
    ) {
        val archiveHikeMealIntakeSheet = ArchiveHikeMealIntakeSheet(
            id = id,
            hikeId = hikeId,
            name = name,
            numberOfday = numberOfday,
            typeMeal = typeMeal
        )
        hikeDao.updateArchiveHikeMealIntakeSheet(archiveHikeMealIntakeSheet)
    }

    // ArchiveHikeProductsParticipants
    suspend fun getAllArchiveHikeProductsParticipantsFlow(): Flow<List<ArchiveHikeProductsParticipants>> {
        return hikeDao.getAllArchiveHikeProductsParticipantsFlow()
    }

    suspend fun getAllListArchiveHikeProductsParticipants(): List<ArchiveHikeProductsParticipants> {
        return hikeDao.getAllListArchiveHikeProductsParticipants()
    }

    suspend fun insertArchiveHikeProductsParticipants(
        participantId: Int,
        productsId: Int
    ) {
        val archiveHikeProductsParticipants = ArchiveHikeProductsParticipants(
            participantId = participantId,
            productsId = productsId
        )
        hikeDao.insertArchiveHikeProductsParticipants(archiveHikeProductsParticipants)
    }

    suspend fun deleteArchiveHikeProductsParticipants(archiveHikeProductsParticipants: ArchiveHikeProductsParticipants) {
        hikeDao.deleteArchiveHikeProductsParticipants(archiveHikeProductsParticipants)
    }

    suspend fun updateArchiveHikeProductsParticipants(
        participantId: Int,
        productsId: Int
    ) {
        val archiveHikeProductsParticipants = ArchiveHikeProductsParticipants(
            participantId = participantId,
            productsId = productsId
        )
        hikeDao.updateArchiveHikeProductsParticipants(archiveHikeProductsParticipants)
    }

    // ArchiveHikeProductsMealList

    suspend fun getAllArchiveHikeProductsMealListFlow(): Flow<List<ArchiveHikeProductsMealList>> {
        return hikeDao.getAllArchiveHikeProductsMealListFlow()
    }

    suspend fun getAllListArchiveHikeProductsMealList(): List<ArchiveHikeProductsMealList> {
        return hikeDao.getAllListArchiveHikeProductsMealList()
    }

    suspend fun insertArchiveHikeProductsMealList(
        mealIntakeId: Int,
        productsId: Int
    ) {
        val archiveHikeProductsMealList = ArchiveHikeProductsMealList(
            mealIntakeId = mealIntakeId,
            productsId = productsId
        )
        hikeDao.insertArchiveHikeProductsMealList(archiveHikeProductsMealList)
    }

    suspend fun deleteArchiveHikeProductsMealList(archiveHikeProductsMealList: ArchiveHikeProductsMealList) {
        hikeDao.deleteArchiveHikeProductsMealList(archiveHikeProductsMealList)
    }

    suspend fun updateArchiveHikeProductsMealList(
        mealIntakeId: Int,
        productsId: Int
    ) {
        val archiveHikeProductsMealList = ArchiveHikeProductsMealList(
            mealIntakeId = mealIntakeId,
            productsId = productsId
        )
        hikeDao.updateArchiveHikeProductsMealList(archiveHikeProductsMealList)
    }
}