package com.example.myapplication.ui.create_hike_list_products

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.Equipment
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.domain.ParticipantsEquipmentUseCase
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class CreateHikeListProductsViewModel(private val hikeDao: HikeDao)  : ViewModel() {

    suspend  fun getAllEquipmentFlow(): Flow<List<ListTypeOfProducts>> {
        return ProductsUseCase(hikeDao).getAllListTypeOfProductsFlow()
    }
    suspend  fun upDateEquipment(
        id: Int,
        name:String,
        photo: String,
        weight: Int,
        theVolumeItem: Boolean,
        equipmentInTheCampaign: Boolean
    ) {
        return ParticipantsEquipmentUseCase(hikeDao).upDateEquipment(
            id = id,
            name = name,
            photo = photo,
            weight = weight,
            theVolumeItem = theVolumeItem,
            equipmentInTheCampaign = equipmentInTheCampaign
        )
    }
    suspend  fun getAllEquipmentList(): List<Equipment> {
        return ParticipantsEquipmentUseCase(hikeDao).getAllCollectionEquipmentList()
    }
    suspend fun createHikeEquipment(
        id: Int,
        participantsId: Int,
        name:String,
        photo: String,
        weight: Int,
        partiallyAssembled: Boolean,
        fullyAssembled: Boolean,
        theVolumeItem: Boolean,
        comment: String
    ) {
        return ThisHikeUseCase(hikeDao).insertThisHikeEquipment(
            id = id,
            participantsId = participantsId,
            name = name,
            photo = photo,
            weight = weight,
            partiallyAssembled = partiallyAssembled,
            fullyAssembled = fullyAssembled,
            theVolumeItem = theVolumeItem,
            comment = comment
        )
    }

}