package com.example.mynotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynotesapp.repository.MyNotesRepository

class MyNotesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyNotesViewModel::class.java)) {
            return MyNotesViewModel(MyNotesRepository(application)) as T
        }
        throw IllegalArgumentException("Unknown class found")
    }
}