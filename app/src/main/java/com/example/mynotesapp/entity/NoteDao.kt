package com.example.mynotesapp.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Query("DELETE FROM notes_table")
    fun delete()

    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    fun getAllNotes(): LiveData<List<Note>>
}