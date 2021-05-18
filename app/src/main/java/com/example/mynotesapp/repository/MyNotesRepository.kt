package com.example.mynotesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.entity.MyNoteDao
import com.example.mynotesapp.entity.MyNotesDatabase

class MyNotesRepository(application: Application) {

    private var noteDao: MyNoteDao
    private var allNotes: LiveData<List<MyNote>>
    private val notesDatabase = MyNotesDatabase.getInstance(application)

    init {
        noteDao = notesDatabase.noteDao()
        allNotes = notesDatabase.noteDao().getAllNotes()
    }

    fun insert(note: MyNote) {
        noteDao.insert(note)
    }

    fun delete() {
        noteDao.delete()
    }

    fun getAllNotes(): LiveData<List<MyNote>> {
        return allNotes
    }

}