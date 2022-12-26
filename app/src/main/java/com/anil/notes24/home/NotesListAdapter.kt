package com.anil.notes24.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anil.notes24.databinding.ListItemNoteBinding
import com.anil.notes24.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter() :
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
        fun bind(notes: Note) {
            binding.noteLines.text = notes.note
            binding.date.text = formatDate(Date(notes.createdAt))
        }

        private fun formatDate(date: Date): String{
            val formatter = SimpleDateFormat("MM/dd/yyyy, hh:mm a", Locale.US)
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
}

