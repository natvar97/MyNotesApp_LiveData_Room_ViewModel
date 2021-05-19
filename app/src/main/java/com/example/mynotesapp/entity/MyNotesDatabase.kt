package com.example.mynotesapp.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyNote::class], version = 1)
abstract class MyNotesDatabase : RoomDatabase() {

    abstract fun noteDao(): MyNoteDao

    companion object {
        @Volatile
        private var instance: MyNotesDatabase? = null
        fun getInstance(context: Context): MyNotesDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(
                        context,
                        MyNotesDatabase::class.java,
                        "notes_database"
                    ).build()
            }
            return instance!!
        }
    }
}