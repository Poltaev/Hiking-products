package com.example.myapplication.ui.food_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is food list fragment"
    }
    val text: LiveData<String> = _text
}