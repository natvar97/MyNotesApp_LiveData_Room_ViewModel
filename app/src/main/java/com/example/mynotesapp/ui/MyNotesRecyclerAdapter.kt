package com.example.mynotesapp.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.Constants
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.NoteListItemBinding
import com.example.mynotesapp.entity.MyNote

class MyNotesRecyclerAdapter(private val activity: Activity) :
    RecyclerView.Adapter<MyNotesRecyclerAdapter.NotesViewHolder>() {

    private var notesList = ArrayList<MyNote>()

    inner class NotesViewHolder(itemView: NoteListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val tvComment = itemView.tvComment
        val ivEdit = itemView.ivEdit
        val ivDelete = itemView.ivDelete

        fun bind(note: MyNote) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvComment.text = note.comment
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(notesList[position])

        holder.ivDelete.setOnClickListener {
            if (activity is MainActivity) {
                activity.deleteNote(note)
            }
        }

        holder.ivEdit.setOnClickListener {
            val intent = Intent(activity, AddNewNoteActivity::class.java)
            intent.putExtra(Constants.EXTRA_DISH_DETAILS, note)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun saveData(notes: List<MyNote>) {
        notesList.clear()
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

}