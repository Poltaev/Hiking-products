package com.example.myapplication.ui.this_hike_list_of_equipment

import androidx.lifecycle.ViewModel
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.products.ListTypeOfProducts
import com.example.myapplication.dataBase.thisHike.ThisHikeEquipment
import com.example.myapplication.domain.ProductsUseCase
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow

class ThisHikeListOfEquipmentViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllThisHikeListEquipmentFlow(): Flow<List<ThisHikeEquipment>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeEquipmentFlow()
    }

}