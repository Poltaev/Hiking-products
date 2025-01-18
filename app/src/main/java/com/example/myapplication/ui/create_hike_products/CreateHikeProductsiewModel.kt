package com.example.myapplication.ui.create_hike_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class CreateHikeProductsiewModel(private val hikeDao: HikeDao)  : ViewModel() {
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
}