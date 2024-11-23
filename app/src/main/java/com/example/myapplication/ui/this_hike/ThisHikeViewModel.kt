package com.example.myapplication.ui.this_hike

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThisHikeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is this hike fragment"
    }
    val text: LiveData<String> = _text
}