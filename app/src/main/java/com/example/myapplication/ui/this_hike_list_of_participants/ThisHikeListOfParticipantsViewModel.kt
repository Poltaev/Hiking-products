package com.example.myapplication.ui.this_hike_list_of_participants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.dataBase.HikeDao
import com.example.myapplication.dataBase.thisHike.ThisHikeParticipants
import com.example.myapplication.dataBase.thisHike.ThisHikeProductsParticipants
import com.example.myapplication.domain.ThisHikeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ThisHikeListOfParticipantsViewModel(private val hikeDao: HikeDao) : ViewModel() {
    suspend fun getAllParticipantsFlow(): Flow<List<ThisHikeParticipants>> {
        return ThisHikeUseCase(hikeDao).getAllThisHikeParticipantsFlow()
    }
}