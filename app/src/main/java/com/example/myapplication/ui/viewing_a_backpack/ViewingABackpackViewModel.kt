package com.example.myapplication.ui.viewing_a_backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsMealList
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ViewingABackpackViewModel(private val hikeDao: HikeDao) : ViewModel() {

    fun getListFood(participantsId: Int): MutableList<ThisHikeProducts> {
        val listIdProducts = mutableListOf<Int>()
        val listProducts = mutableListOf<ThisHikeProducts>()
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
        return listProducts
    }

    fun getListEquipment(participantsId: Int): MutableList<ThisHikeEquipment> {
        val listEquipment = mutableListOf<ThisHikeEquipment>()
        viewModelScope.launch(Dispatchers.IO) {
            ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow().collect {
                it.forEach {
                    if (it.participantsId == participantsId) {
                        listEquipment.add(it)
                    }
                }
            }
        }
        return listEquipment
    }
}