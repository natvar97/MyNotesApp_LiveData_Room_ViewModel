package com.example.mynotesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesapp.entity.Note
import com.example.mynotesapp.entity.NoteDao
import com.example.mynotesapp.entity.NotesDatabase

class NotesRepository(application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>
    private val notesDatabase = NotesDatabase.getInstance(application)

    init {
        noteDao = notesDatabase.noteDao()
        allNotes = notesDatabase.noteDao().getAllNotes()
    }

    fun insert(note: Note) {
        noteDao.insert(note)
    }

    fun delete() {
        noteDao.delete()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

}