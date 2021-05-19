package com.example.mynotesapp.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyNoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: MyNote)

    @Query("DELETE FROM notes_table")
    suspend fun delete()

    @Query("SELECT * FROM notes_table ORDER BY title ASC")
    fun getAllNotes(): Flow<List<MyNote>>
}