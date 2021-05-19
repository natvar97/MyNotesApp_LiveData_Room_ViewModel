package com.example.mynotesapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotesapp.Constants
import com.example.mynotesapp.MyNotesApplication
import com.example.mynotesapp.R
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.viewmodel.MyNotesViewModel
import com.example.mynotesapp.viewmodel.MyNotesViewModelFactory

class AddNewNoteActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var etComment: EditText
    private lateinit var btnSave: Button
    private val myNotesViewModel: MyNotesViewModel by viewModels {
        MyNotesViewModelFactory((application!! as MyNotesApplication).notesRepository)
    }

    private var mEditNotes: MyNote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_note_layout)

        init()

        if (intent.hasExtra(Constants.EXTRA_DISH_DETAILS)) {
            mEditNotes = intent.getParcelableExtra(Constants.EXTRA_DISH_DETAILS)
        }

        mEditNotes?.let {
            if (it.id != 0) {
                etTitle.setText(mEditNotes!!.title)
                etDescription.setText(mEditNotes!!.description)
                etComment.setText(mEditNotes!!.comment)
            }
        }

        btnSave.setOnClickListener {
            if (checkConditions()) {
                var noteId = 0

                mEditNotes?.let {
                    noteId = it.id
                }

                val myNote = MyNote(
                    etTitle.text.toString(),
                    etDescription.text.toString(),
                    etComment.text.toString(),
                    noteId
                )

                if (noteId == 0) {
                    myNotesViewModel.insert(myNote)
                } else {
                    myNotesViewModel.update(myNote)
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(this, "Please provide all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkConditions(): Boolean {
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