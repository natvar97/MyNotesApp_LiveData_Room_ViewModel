package com.example.mynotesapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class MyNote(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "comment")
    var comment: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
)