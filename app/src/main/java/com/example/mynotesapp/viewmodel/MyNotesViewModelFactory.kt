package com.example.mynotesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.repository.MyNotesRepository

class MyNotesViewModelFactory(private val myNotesRepository: MyNotesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyNotesViewModel::class.java)) {
            return MyNotesViewModel(myNotesRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class found")
    }
}