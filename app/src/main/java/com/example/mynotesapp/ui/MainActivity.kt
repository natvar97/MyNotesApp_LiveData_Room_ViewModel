package com.example.mynotesapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.MyNotesApplication
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.ActivityMainBinding
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.viewmodel.MyNotesViewModel
import com.example.mynotesapp.viewmodel.MyNotesViewModelFactory
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView
    private lateinit var notesRecyclerAdapter: MyNotesRecyclerAdapter
    private val notesViewModel: MyNotesViewModel by viewModels {
        MyNotesViewModelFactory((application!! as MyNotesApplication).notesRepository)
    }

    private lateinit var mBinding : ActivityMainBinding
//    private lateinit var fab: FloatingActionButton
//    private lateinit var btnNews: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

//        fab = findViewById(R.id.fab)
//        btnNews = findViewById(R.id.btn_news)
        attachRecyclerView()

        mBinding.fab.setOnClickListener {
            openAddNewNotePage()
        }

        mBinding.btnNews.setOnClickListener {
            openNewsPage()
        }

        notesViewModel.getAllNotes().observe(this, Observer { notesList ->
            notesList?.let {
                notesRecyclerAdapter.saveData(notesList)
            }
        })

    }

    fun deleteNote(myNote: MyNote) {
        notesViewModel.delete(myNote)
    }

    private fun attachRecyclerView() {
//        recyclerView = findViewById(R.id.recyclerView)
        notesRecyclerAdapter = MyNotesRecyclerAdapter(this)
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerView.adapter = notesRecyclerAdapter
    }

    private fun openAddNewNotePage() {
        val intent = Intent(this, AddNewNoteActivity::class.java)
        startActivity(intent)
    }

    private fun openNewsPage() {
        startActivity(Intent(this, NewsPageActivity::class.java))
    }


}