package com.anil.notes24.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.notes24.databinding.ListItemNoteBinding
import com.anil.notes24.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(val listener: NotesListAdapterListener) :
    ListAdapter<Note, NotesListAdapter.NoteItemViewHolder>(NoteDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        return NoteItemViewHolder(
            binding = ListItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteItemViewHolder(val binding: ListItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.title.text = note.title
            binding.note.text = note.note.replace("\n", ", ")
            binding.date.text = formatDate(Date(note.createdAt))

            binding.root.setOnClickListener {
                listener.onNoteClicked(note)
            }
        }

        private fun formatDate(date: Date): String {
            val formatter = SimpleDateFormat("EEE, d MMM, hh:mm a", Locale.US)
            return formatter.format(date)
        }
    }

    class NoteDiffCallBack : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    interface NotesListAdapterListener {
        fun onNoteClicked(note: Note)
    }
}

