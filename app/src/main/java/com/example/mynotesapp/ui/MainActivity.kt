package com.example.mynotesapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.MyNotesApplication
import com.example.mynotesapp.R
import com.example.mynotesapp.viewmodel.MyNotesViewModel
import com.example.mynotesapp.viewmodel.MyNotesViewModelFactory
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var notesRecyclerAdapter: MyNotesRecyclerAdapter
    private val notesViewModel: MyNotesViewModel by viewModels {
        MyNotesViewModelFactory((application!! as MyNotesApplication).notesRepository)
    }
    private lateinit var fab: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab = findViewById(R.id.fab)

        attachRecyclerView()

        fab.setOnClickListener {
            openAddNewNotePage()
        }

        notesViewModel.getAllNotes().observe(this, Observer { notesList ->
            notesList?.let {
                notesRecyclerAdapter.saveData(notesList)
            }
        })

    }

    private fun attachRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        notesRecyclerAdapter = MyNotesRecyclerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = notesRecyclerAdapter
    }

    private fun openAddNewNotePage() {
        val intent = Intent(this, AddNewNoteActivity::class.java)
        startActivity(intent)
    }


}