package com.example.mynotesapp.entity

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MyNoteDao {
    @Insert
    suspend fun insert(note: MyNote)

    @Update
    suspend fun update(note : MyNote)

    @Delete
    suspend fun delete(note : MyNote)

    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    fun getAllNotes(): Flow<List<MyNote>>
}