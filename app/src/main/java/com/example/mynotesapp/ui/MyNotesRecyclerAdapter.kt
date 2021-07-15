package com.example.mynotesapp.ui

import android.app.Activity
import android.content.Intent
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotesapp.Constants
import com.example.mynotesapp.MyNotesApplication
import com.example.mynotesapp.R
import com.example.mynotesapp.databinding.NoteListItemBinding
import com.example.mynotesapp.entity.MyNote
import com.example.mynotesapp.viewmodel.ListItemViewModel
import com.example.mynotesapp.viewmodel.MyNotesViewModelFactory

class MyNotesRecyclerAdapter(private val activity: Activity) :
    RecyclerView.Adapter<MyNotesRecyclerAdapter.NotesViewHolder>() {

    private var notesList = ArrayList<MyNote>()
    private var isEnable: Boolean = false
    private var isSelectAll: Boolean = false
    private var mSelectedItems = ArrayList<MyNote>()
    private lateinit var listItemViewModel: ListItemViewModel

    inner class NotesViewHolder(itemView: NoteListItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val tvTitle = itemView.tvTitle
        val tvDescription = itemView.tvDescription
        val tvComment = itemView.tvComment

        //        val ivEdit = itemView.ivEdit
//        val ivDelete = itemView.ivDelete
        val ivChecked = itemView.ivChecked

        fun bind(note: MyNote) {
            tvTitle.text = note.title
            tvDescription.text = note.description
            tvComment.text = note.comment
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = NoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        listItemViewModel = ViewModelProvider(
            activity as FragmentActivity,
            MyNotesViewModelFactory((activity.application as MyNotesApplication).notesRepository)
        ).get(ListItemViewModel::class.java)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.bind(notesList[position])

        /*
            now i don't need this delete and edit button for actions
            i done edit by opening the edit using on click
            and delete using multiple item delete in notes
         */

//        holder.ivDelete.setOnClickListener {
//            if (activity is MainActivity) {
//                activity.deleteNote(note)
//            }
//        }
//
//        holder.ivEdit.setOnClickListener {
//            val intent = Intent(activity, AddNewNoteActivity::class.java)
//            intent.putExtra(Constants.EXTRA_DISH_DETAILS, note)
//            activity.startActivity(intent)
//        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                if (!isEnable) {
                    val callback = object : ActionMode.Callback {
                        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                            val menuInflater = mode?.menuInflater
                            menuInflater?.inflate(R.menu.menu, menu)
                            return true
                        }

                        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                            isEnable = true
                            clickItem(holder)

                            listItemViewModel.getListSize().observe(activity as FragmentActivity) {
                                mode?.title = "Selected $it"
                            }

                            return true
                        }

                        override fun onActionItemClicked(
                            mode: ActionMode?,
                            item: MenuItem?
                        ): Boolean {
                            when (item?.itemId) {
                                R.id.menu_delete -> {
                                    for (listItem in mSelectedItems) {
                                        if (activity is MainActivity) {
                                            activity.deleteNote(listItem)
                                        }
                                    }

                                    if (notesList.size == 0) {
                                        if (activity is MainActivity) {
                                            activity.visibleTvNoDataFound()
                                        }
                                    }
                                    mode?.finish()
                                }
                                R.id.menu_select_all -> {
                                    if (mSelectedItems.size == notesList.size) {
                                        isSelectAll = false
                                        mSelectedItems.clear()
                                    } else {
                                        isSelectAll = true
                                        mSelectedItems.clear()
                                        mSelectedItems.addAll(notesList)
                                    }
                                    listItemViewModel.setListSize("${mSelectedItems.size}")
                                    notifyDataSetChanged()
                                }
                            }

                            return true
                        }

                        override fun onDestroyActionMode(mode: ActionMode?) {
                            isEnable = false
                            isSelectAll = false
                            mSelectedItems.clear()
                            notifyDataSetChanged()
                        }

                    }
                    (v?.context as AppCompatActivity).startActionMode(callback)

                } else {
                    clickItem(holder)
                }
                return true
            }

        })

        holder.itemView.setOnClickListener {
            if (isEnable) {
                clickItem(holder)
            } else {
                // open new activity to edit note
                val intent = Intent(activity, AddNewNoteActivity::class.java)
                intent.putExtra(Constants.EXTRA_DISH_DETAILS, note)
                activity.startActivity(intent)
            }
        }

        if (isSelectAll) {
            holder.ivChecked.visibility = View.VISIBLE
        } else {
            holder.ivChecked.visibility = View.GONE
        }

    }

    private fun clickItem(holder: MyNotesRecyclerAdapter.NotesViewHolder) {
        val selectedItem = notesList.get(holder.adapterPosition)

        if (holder.ivChecked.visibility == View.GONE) {
            holder.ivChecked.visibility = View.VISIBLE
            mSelectedItems.add(selectedItem)
        } else {
            holder.ivChecked.visibility = View.GONE
            mSelectedItems.remove(selectedItem)
        }

        listItemViewModel.setListSize("${mSelectedItems.size}")
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