package com.example.mynotesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.mynotesapp.R

class AddNewNoteActivity : AppCompatActivity() {

    private lateinit var etTitle : EditText
    private lateinit var etDescription : EditText
    private lateinit var etComment : EditText
    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_note_layout)

        init()

        btnSave.setOnClickListener {
            if (checkConditions()){}
        }


    }

    private fun checkConditions() : Boolean {
        return !(TextUtils.isEmpty(etTitle.text.toString().trim { it <= ' ' }) ||
                TextUtils.isEmpty(etDescription.text.toString().trim { it <= ' ' }) ||
                TextUtils.isEmpty(etComment.text.toString().trim { it <= ' ' }))
    }

    private fun init() {
        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        etComment = findViewById(R.id.et_comment)
        btnSave = findViewById(R.id.btn_save)
    }
}