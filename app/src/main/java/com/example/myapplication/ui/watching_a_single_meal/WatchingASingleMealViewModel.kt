package com.example.myapplication.ui.watching_a_single_meal

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeListIdProductsInMeal
import com.example.myapplication.dataBase.thisHike.ThisHikeMealIntakeSheet
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class WatchingASingleMealViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllThisHikeListIdProductsInMeal(): List<ThisHikeListIdProductsInMeal> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeListIdProductsInMeal()
    }
    suspend fun getAllListFood(): List<ThisHikeProducts> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
    }
    suspend fun getAllPartisipant(): List<ThisHikeParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeParticipants()
    }
    suspend fun getAllProductsParticipant(): List<ThisHikeProductsParticipants> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()
    }
    suspend fun upDateProducts(
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
         ThisHikeUseCase(hikeDao).updateThisHikeProducts(
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
    }
    suspend fun upDateParticipant(
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
        comment: String
    ) {
        ThisHikeUseCase(hikeDao).updateThisHikeParticipants(
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
    }
    suspend fun deleteParticipantProducts(productsParticipants: ThisHikeProductsParticipants){
        return ThisHikeUseCase(hikeDao).deleteThisHikeProductsParticipants(productsParticipants)
    }
    suspend fun deleteMealProducts(productsMeal: ThisHikeProductsMealList){
        return ThisHikeUseCase(hikeDao).deleteThisHikeProductsMealList(productsMeal)
    }
    suspend fun getAllThisHikeProductsMealList(): List<ThisHikeProductsMealList> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeProductsMealList()
    }
    suspend fun deleteMealProducts(listMeal: ThisHikeMealIntakeSheet){
        return ThisHikeUseCase(hikeDao).deleteThisHikeMealIntakeSheet(listMeal)
    }
    suspend fun getAllThisHikeMealIntakeSheet(): List<ThisHikeMealIntakeSheet> {
        return ThisHikeUseCase(hikeDao).getAllListThisHikeMealIntakeSheet()
    }
    suspend fun deleteProducts(product: ThisHikeProducts){
        return ThisHikeUseCase(hikeDao).deleteThisHikeProducts(product)
    }
}