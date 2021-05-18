package com.example.mynotesapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "notes_table")
data class Note(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "comment")
    var comment: String
)