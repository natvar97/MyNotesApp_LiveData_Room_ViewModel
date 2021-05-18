package com.example.mynotesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.R
import com.example.mynotesapp.entity.MyNote

class MyNotesRecyclerAdapter : RecyclerView.Adapter<MyNotesRecyclerAdapter.NotesViewHolder>() {

    private var notesList = ArrayList<MyNote>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvComment = itemView.findViewById<TextView>(R.id.tv_comment)

        fun bind(note: MyNote) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvComment.text = note.comment
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun saveData(notes: List<MyNote>) {
        notesList.addAll(notes)
        notifyDataSetChanged()
    }

}