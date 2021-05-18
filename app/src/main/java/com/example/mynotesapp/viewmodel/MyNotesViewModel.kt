package com.example.mynotesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.repository.MyNotesRepository

class MyNotesViewModel(private val notesRepository: MyNotesRepository) : ViewModel() {
//    private var application = Application()
//    private var notesRepository = NotesRepository(application)
    private var allNotes = this.notesRepository.getAllNotes()

    fun insert(note : MyNote){
        notesRepository.insert(note)
    }

    fun delete() {
        notesRepository.delete()
    }

    fun getAllNotes() : LiveData<List<MyNote>> {
        return allNotes
    }

}