package com.example.myapplication.ui.list_of_participants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListOfParticipantsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is list of participants"
    }
    val text: LiveData<String> = _text
}