package com.example.mynotesapp.viewmodel

import androidx.lifecycle.*
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.repository.MyNotesRepository
import kotlinx.coroutines.launch

class MyNotesViewModel(private val notesRepository: MyNotesRepository) : ViewModel() {

    fun insert(note : MyNote) = viewModelScope.launch{
        notesRepository.insert(note)
    }

    fun delete() = viewModelScope.launch{
        notesRepository.delete()
    }

    fun getAllNotes() : LiveData<List<MyNote>> = notesRepository.getAllNotes().asLiveData()

}