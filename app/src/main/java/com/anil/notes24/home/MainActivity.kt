package com.anil.notes24.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.anil.notes24.createnote.AddNoteActivity
import com.anil.notes24.databinding.ActivityMainBinding
import com.anil.notes24.model.Note

class MainActivity : AppCompatActivity(), NotesListAdapter.NotesListAdapterListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotesListAdapter(this)
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        viewModel.notes.observe(this) {
            Log.e("MainActivity", "$it")
            binding.emptyMessage.isVisible = it.isEmpty()
            adapter.submitList(it)
        }
    }

    override fun onNoteClicked(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java).apply {
            putExtra("note", note)
        }

        startActivity(intent)
    }
}