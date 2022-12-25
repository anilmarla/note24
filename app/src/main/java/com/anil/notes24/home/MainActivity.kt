package com.anil.notes24.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.anil.notes24.R
import com.anil.notes24.databinding.ActivityMainBinding
import com.anil.notes24.model.Note
import com.anil.notes24.notes.Notes
import com.anil.notes24.notes.NotesListAdapter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesListAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotesListAdapter()
        binding.recyclerView.adapter = adapter

        binding.fab.setOnClickListener {

        }

        viewModel.loadNotes()

        viewModel.notes.observe(this) {
            Log.e("MainActivity", "$it")
            adapter.submitList(it)
        }
    }
}