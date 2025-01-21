package com.example.myapplication.ui.create_hike_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListProducts
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.dataBase.thisHike.ThisHike
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class CreateHikeProductsiewModel(private val hikeDao: HikeDao)  : ViewModel() {
    suspend  fun getAllThisHike(): List<ThisHike> {
        return ThisHikeUseCase(hikeDao).getAllListThisHike()
    }
    suspend  fun getAllProductsFlow(): Flow<List<Products>> {
        return ProductsUseCase(hikeDao).getAllProductsFlow()
    }
    suspend  fun upDateProducts(
        id: Int,
        name:String,
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        return ProductsUseCase(hikeDao).upDateProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
    }
    suspend  fun getAllProductsList(): List<Products> {
        return ProductsUseCase(hikeDao).getAllProductsList()
    }
    suspend  fun getAllThisHikeProductsList(): List<ThisHikeProducts> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
    }
    suspend  fun getAllParticipants(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }
    suspend  fun getAllListProducts(): List<ListProducts> {
        return ProductsUseCase(hikeDao).getAllListProductsList()
    }
    suspend  fun getAllThisHikeMealIntakeSheet(): List<ThisHikeMealIntakeSheet> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
    }
    suspend fun createThisHikeProductsMealList(
        mealIntakeId: Int,
        productsId: Int
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeProductsMealList(
            mealIntakeId = mealIntakeId,
            productsId = productsId
        )
    }
    suspend  fun getAllListProductsList(): List<ListTypeOfProducts> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsList()
    }
    suspend fun createHikeProducts(
        id: Int,
        name:String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeProducts(
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
            comment = comment
        )
    }
    suspend fun createMealSheetProducts(
        id: Int,
        hikeId: Int,
        name:String,
        numberOfday: Int,
        typeMeal: String
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeMealIntakeSheet(
            id = id,
            hikeId = hikeId,
            name = name,
            numberOfday = numberOfday,
            typeMeal = typeMeal
        )
    }
    suspend  fun upDateThisHikeProducts(
        id: Int,
        name:String,
        weightForPerson: Int,
        packageWeight: Int,
        theWeightOfOneMeal: Int,
        weightOnTheHike: Int,
        remainingWeight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).updateThisHikeProducts(
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
            comment = comment
        )
    }
}