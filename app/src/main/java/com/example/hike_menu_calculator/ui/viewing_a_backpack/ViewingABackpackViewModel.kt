package com.example.hike_menu_calculator.ui.viewing_a_backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeEquipment
import com.example.hike_menu_calculator.dataBase.thisHike.ThisHikeProducts
import com.example.hike_menu_calculator.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ViewingABackpackViewModel(private val hikeDao: HikeDao) : ViewModel() {

    suspend fun getListFood(participantsId: Int): MutableList<ThisHikeProducts> {
        val listIdProducts = mutableListOf<Int>()
        val listProducts = mutableListOf<ThisHikeProducts>()
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                val listProductParticipant =
                    ThisHikeUseCase(hikeDao).getAllListThisHikeProductsParticipants()

                listProductParticipant.forEach {
                    if (it.participantId == participantsId) {
                        listIdProducts.add(it.productsId)
                    }
                }
                val listThisHikeProduct = ThisHikeUseCase(hikeDao).getAllListThisHikeProducts()
                listIdProducts.forEach {
                    listThisHikeProduct.forEach { item ->
                        if (it == item.id) listProducts.add(item)
                    }
                }
            }
        }
        return listProducts
    }

    suspend fun getListEquipment(participantsId: Int): MutableList<ThisHikeEquipment> {
        val listEquipment = mutableListOf<ThisHikeEquipment>()
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow().collect {
                    it.forEach {
                        if (it.participantsId == participantsId) {
                            listEquipment.add(it)
                        }
                    }
                }
            }
        }
        return listEquipment
    }
}