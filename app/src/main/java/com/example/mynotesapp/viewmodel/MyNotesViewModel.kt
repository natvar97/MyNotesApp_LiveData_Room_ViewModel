package com.example.mynotesapp.viewmodel

import androidx.lifecycle.*
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.repository.MyNotesRepository
import kotlinx.coroutines.launch

class MyNotesViewModel(private val notesRepository: MyNotesRepository) : ViewModel() {

    fun insert(note : MyNote) = viewModelScope.launch{
        notesRepository.insert(note)
    }

    fun update(note : MyNote) = viewModelScope.launch {
        notesRepository.update(note)
    }

    fun delete(note : MyNote) = viewModelScope.launch{
        notesRepository.delete(note)
    }

    fun getAllNotes() : LiveData<List<MyNote>> = notesRepository.getAllNotes().asLiveData()

}