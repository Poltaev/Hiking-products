package com.example.myapplication.ui.view_the_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.domain.ProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ViewTheMenuViewModel(private val hikeDao: HikeDao) : ViewModel() {
    val allEquipment = this.hikeDao.getAllEquipmentFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()

        )
    fun addProducts() {
        viewModelScope.launch {
            ProductsUseCase(hikeDao).loadProducts(
                id = 1,
                name = "name",
                weightForPerson = 1.0,
                packageWeight = 1.0,
                weWillUseItInTheCurrentCampaign = false
            )
        }
    }
}