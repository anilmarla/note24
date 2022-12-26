package com.anil.notes24.createnote

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anil.notes24.R
import com.anil.notes24.databinding.ActivityAddNoteBinding
import com.anil.notes24.model.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // reading note from intent
        note = intent.getParcelableExtra("note")

        if (note == null) {
            // creating new note
            title = getString(R.string.add_note)
        } else {
            // editing note
            title = getString(R.string.edit_note)
            binding.txtInputEdit.text = Editable.Factory.getInstance().newEditable(note?.note)
            binding.btnCreate.text = getString(R.string.update_note)
        }

        // open keyboard
        binding.txtInputEdit.requestFocus()

        binding.btnCreate.setOnClickListener {
            val txt = binding.txtInputEdit.text

            if (note == null) {
                viewModel.addNote(txt.toString())
                Toast.makeText(this, "Note is added!", Toast.LENGTH_LONG).show()
            } else {
                // update note text
                note?.note = txt.toString()

                viewModel.updateNote(note)
                Toast.makeText(this, "Note is updated!", Toast.LENGTH_LONG).show()
            }

            finish()
        }
    }

    private fun deleteNote() {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Note is deleted successfully!", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (note != null) {
            menuInflater.inflate(R.menu.toolbar_menu, menu)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                MaterialAlertDialogBuilder(this).setTitle(resources.getString(R.string.are_you_sure))
                    .setMessage(getString(R.string.delete_note))
                    .setNegativeButton(resources.getString(R.string.no)) { _, _ ->
                        // Respond to negative button press
                    }
                    .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                        // Respond to positive button press
                        deleteNote()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

