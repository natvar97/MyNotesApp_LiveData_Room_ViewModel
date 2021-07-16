package com.example.mynotesapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Bindable
import androidx.databinding.BindingMethod
import androidx.databinding.DataBindingUtil
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

    private lateinit var notesRecyclerAdapter: MyNotesRecyclerAdapter
    private val notesViewModel: MyNotesViewModel by viewModels {
        MyNotesViewModelFactory((application!! as MyNotesApplication).notesRepository)
    }

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mBinding.fab.setOnClickListener {
            openAddNewNotePage()
        }

        mBinding.btnNews.setOnClickListener {
            openNewsPage()
        }

        notesRecyclerAdapter = MyNotesRecyclerAdapter(this)

        notesViewModel.getAllNotes().observe(this, Observer { notesList ->
            notesList?.let {
                notesRecyclerAdapter.saveData(notesList)
            }
        })

        mBinding.noteAdapter = notesRecyclerAdapter
    }

    fun visibleTvNoDataFound() {
        mBinding.tvDataNotFound.visibility = View.VISIBLE
    }

    fun deleteNote(myNote: MyNote) {
        notesViewModel.delete(myNote)
    }

    fun openNewsPage() {
        startActivity(Intent(this, NewsPageActivity::class.java))
    }

    fun openAddNewNotePage() {
        val intent = Intent(this, AddNewNoteActivity::class.java)
        startActivity(intent)
    }


}