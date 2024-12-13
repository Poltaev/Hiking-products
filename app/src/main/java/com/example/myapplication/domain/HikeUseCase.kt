package com.example.myapplication.domain

import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.Products
import kotlinx.coroutines.flow.Flow

class HikeUseCase(private val hikeDao: HikeDao) {
    // Products
    suspend fun getAllCollectionProductsFlow(): Flow<List<Products>> {
        return hikeDao.getAllProductsFlow()
    }

    suspend fun getAllCollectionProductsList(): List<Products> {
        return hikeDao.getAllListProducts()
    }

    suspend fun loadProducts(
        id: Int,
        name: String,
        weightForPerson: Double,
        packageWeight: Double,
        thePhaseOfEating: String,
        incompletePurchase: Boolean,
        fullPurchase: Boolean,
        colorOfBackground: String,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val ProductsForLoad = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            thePhaseOfEating = thePhaseOfEating,
            incompletePurchase = incompletePurchase,
            fullPurchase = fullPurchase,
            colorOfBackground = colorOfBackground,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
        hikeDao.insertOneProducts(ProductsForLoad)
    }

    suspend fun deleteProducts(products: Products) {
        hikeDao.deleteOneProducts(products)
    }

    suspend fun upDateProducts(
        id: Int,
        name: String,
        weightForPerson: Double,
        packageWeight: Double,
        thePhaseOfEating: String,
        incompletePurchase: Boolean,
        fullPurchase: Boolean,
        colorOfBackground: String,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        val products = Products(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            thePhaseOfEating = thePhaseOfEating,
            incompletePurchase = incompletePurchase,
            fullPurchase = fullPurchase,
            colorOfBackground = colorOfBackground,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
        hikeDao.updateOneProducts(products)
    }

// Equipment

    suspend fun getAllCollectionEquipmentFlow(): Flow<List<Equipment>> {
        return hikeDao.getAllEquipmentFlow()
    }

    suspend  fun getAllCollectionEquipmentList(): List<Equipment> {
        return hikeDao.getAllListEquipment()
    }

    suspend fun loadEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Double
    ) {
        val equipmentForLoad = Equipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight
        )
        hikeDao.insertOneEquipment(equipmentForLoad)
    }

    suspend fun deleteEquipment(equipment: Equipment) {
        hikeDao.deleteOneEquipment(equipment)
    }

    suspend fun upDateEquipment(
        id: Int,
        name: String,
        photo: String,
        weight: Double
    ) {
        val equipment = Equipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight
        )
        hikeDao.updateOneEquipment(equipment)
    }

    // Participants
    suspend  fun getAllCollectionParticipantsFlow(): Flow<List<Participants>> {
        return hikeDao.getAllParticipantsFlow()
    }

    suspend fun getAllCollectionParticipantsList(): List<Participants> {
        return hikeDao.getAllListParticipants()
    }

    suspend fun loadParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: Int,
        maximumPortableWeight: Double,
        weightOfPersonalItems: Double,
        participationInTheCampaign: Boolean
    ) {
        val participantsForLoad = Participants(
            id = id,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            participationInTheCampaign = participationInTheCampaign
        )
        hikeDao.insertOneParticipant(participantsForLoad)
    }

    suspend fun deleteParticipants(participants: Participants) {
        hikeDao.deleteOneParticipant(participants)
    }

    suspend fun upDateParticipants(
        id: Int,
        photo: String,
        firstName: String,
        lastName: String,
        gender: String,
        age: Int,
        maximumPortableWeight: Double,
        weightOfPersonalItems: Double,
        participationInTheCampaign: Boolean
    ) {
        val participants = Participants(
            id = id,
            photo = photo,
            firstName = firstName,
            lastName = lastName,
            gender = gender,
            age = age,
            maximumPortableWeight = maximumPortableWeight,
            weightOfPersonalItems = weightOfPersonalItems,
            participationInTheCampaign = participationInTheCampaign
        )
        hikeDao.updateOneParticipant(participants)
    }
}