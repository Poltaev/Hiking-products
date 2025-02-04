package com.example.hike_menu_calculator.ui.viewing_the_product

import androidx.lifecycle.ViewModel
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.products.Products
import com.example.hike_menu_calculator.domain.ProductsUseCase

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
        theSoleOwner: Boolean,
        nameOwner: String,
        idOwner: Int,
        useTheWholePackInOneMeal: Boolean,
        weWillUseItInTheCurrentCampaign: Boolean
    ) {
        ProductsUseCase(hikeDao).loadProducts(
            id = id,
            name = name,
            weightForPerson = weightForPerson,
            packageWeight = packageWeight,
            theVolumeItem = theVolumeItem,
            theSoleOwner = theSoleOwner,
            nameOwner = nameOwner,
            idOwner = idOwner,
            useTheWholePackInOneMeal = useTheWholePackInOneMeal,
            weWillUseItInTheCurrentCampaign = weWillUseItInTheCurrentCampaign
        )
    }
}