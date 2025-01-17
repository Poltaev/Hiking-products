package com.example.myapplication.ui.viewing_the_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Participants
import com.example.myapplication.dataBase.products.Products
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewingTheProductViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllProductsList(): List<Products> {
        return ProductsUseCase(hikeDao).getAllProductsList()
    }

    suspend fun deleteProducts(products: Products){
        return ProductsUseCase(hikeDao).deleteProducts(products)
    }
    suspend fun addProducts(
        id: Int,
        name: String,
        weightForPerson: Int,
        packageWeight: Int,
        theVolumeItem: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        ProductsUseCase(hikeDao).loadProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
    }
}