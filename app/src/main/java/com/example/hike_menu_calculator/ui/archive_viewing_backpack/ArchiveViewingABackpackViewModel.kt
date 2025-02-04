package com.example.hike_menu_calculator.ui.archive_viewing_backpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hike_menu_calculator.dataBase.HikeDao
import com.example.hike_menu_calculator.dataBase.archive.ArchiveEquipment
import com.example.hike_menu_calculator.dataBase.archive.ArchiveProducts
import com.example.hike_menu_calculator.domain.ArchiveHikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArchiveViewingABackpackViewModel(private val hikeDao: HikeDao) : ViewModel() {

    fun getListFood(participantsId: Int): MutableList<ArchiveProducts> {
        val listIdProducts = mutableListOf<Int>()
        val listProducts = mutableListOf<ArchiveProducts>()
        viewModelScope.launch(Dispatchers.IO) {
            val listProductParticipant =
                ArchiveHikeUseCase(hikeDao).getAllListArchiveHikeProductsParticipants()

            listProductParticipant.forEach {
                if (it.participantId == participantsId) {
                    listIdProducts.add(it.productsId)
                }
            }
            val listThisHikeProduct = ArchiveHikeUseCase(hikeDao).getAllListArchiveProducts()
            listIdProducts.forEach {
                listThisHikeProduct.forEach { item ->
                    if (it == item.id) listProducts.add(item)
                }
            }
        }
        return listProducts
    }

    fun getListEquipment(participantsId: Int): MutableList<ArchiveEquipment> {
        val listEquipment = mutableListOf<ArchiveEquipment>()
        viewModelScope.launch(Dispatchers.IO) {
            ArchiveHikeUseCase(hikeDao).getAllArchiveEquipmentFlow().collect {
                it.forEach {
                    if (it.participantId == participantsId) {
                        listEquipment.add(it)
                    }
                }
            }
        }
        return listEquipment
    }
}