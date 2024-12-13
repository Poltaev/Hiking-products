package com.example.myapplication.ui.this_hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.Products

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThisHikeViewModel(private val hikeDao: HikeDao) : ViewModel() {
    val allProducts = this.hikeDao.getAllProductsFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
    val allEquipment = this.hikeDao.getAllEquipmentFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
    fun addProducts() {
        viewModelScope.launch {
            hikeDao.insertOneProducts(
                Products(
                    id = 1,
                    name = "name",
                    weightForPerson = 1.0,
                    packageWeight = 1.0,
                    thePhaseOfEating = "breakfeast",
                    incompletePurchase = false,
                    fullPurchase = false,
                    colorOfBackground = "red",
                    weWillUseItInTheCurrentCampaign = false
                )
            )
        }
    }
    fun addEquipment() {
        viewModelScope.launch {
            hikeDao.insertOneEquipment(
                Equipment(
                    id = 1,
                    name = "name",
                    photo = "photo",
                    weight = 1.2
                )
            )
        }
    }
}