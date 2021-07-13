package com.example.mynotesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListItemViewModel : ViewModel() {

    private val listSize = MutableLiveData<String>()

    fun setListSize(text: String) {
        listSize.postValue(text)
    }

    fun getListSize(): LiveData<String> {
        return listSize
    }

}