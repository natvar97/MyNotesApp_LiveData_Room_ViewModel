package com.example.mynotesapp.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.entity.MyNoteDao
import kotlinx.coroutines.flow.Flow

class MyNotesRepository(private var noteDao: MyNoteDao) {

    @WorkerThread
    suspend fun insert(note: MyNote) {
        noteDao.insert(note)
    }

    @WorkerThread
    suspend fun update(note : MyNote) {
        noteDao.update(note)
    }

    @WorkerThread
    suspend fun delete(note : MyNote) {
        noteDao.delete(note)
    }

    fun getAllNotes(): Flow<List<MyNote>> {
        return noteDao.getAllNotes()
    }

}