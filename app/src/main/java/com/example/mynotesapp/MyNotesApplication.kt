package com.example.mynotesapp

import android.app.Application
import com.example.mynotesapp.entity.MyNotesDatabase
import com.example.mynotesapp.repository.MyNotesRepository

class MyNotesApplication : Application() {

    private val database by lazy {
        MyNotesDatabase.getInstance(this@MyNotesApplication)
    }

    val notesRepository by lazy {
        MyNotesRepository(database.noteDao())
    }

}