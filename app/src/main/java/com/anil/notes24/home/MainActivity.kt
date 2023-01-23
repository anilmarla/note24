package com.anil.notes24.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.anil.notes24.R
import com.anil.notes24.createnote.AddNoteActivity
import com.anil.notes24.databinding.ActivityMainBinding
import com.anil.notes24.model.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity(), NotesListAdapter.NotesListAdapterListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesListAdapter
    private val viewModel: MainViewModel by viewModels()
    private var isNotesPresent: Boolean = false

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

            isNotesPresent = it.isNotEmpty()
            invalidateOptionsMenu()
        }
    }

    override fun onNoteClicked(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java).apply {
            putExtra("note", note)
        }

        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isNotesPresent) {
            menuInflater.inflate(R.menu.menu_home, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                MaterialAlertDialogBuilder(this).setTitle(resources.getString(R.string.are_you_sure))
                    .setMessage(getString(R.string.delete_all_notes))
                    .setNegativeButton(resources.getString(R.string.no)) { _, _ ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                        viewModel.deleteAll()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}