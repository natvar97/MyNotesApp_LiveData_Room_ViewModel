package com.example.mynotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynotesapp.entity.Note
import com.example.mynotesapp.repository.NotesRepository

open class NotesViewModel : ViewModel() {
    private var application = Application()
    private var notesRepository = NotesRepository(application)
    private var allNotes = this.notesRepository.getAllNotes()

    fun insert(note : Note){
        notesRepository.insert(note)
    }

    fun delete() {
        notesRepository.delete()
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return allNotes
    }

}