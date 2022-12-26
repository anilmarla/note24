package com.anil.notes24.createnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.anil.notes24.R
import com.anil.notes24.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private val viewModel: AddNoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val txt = binding.txtInputEdit.text

        binding.btnCreate.setOnClickListener {
            viewModel.addNote(txt.toString())

            Toast.makeText(this, "Note is added!", Toast.LENGTH_LONG).show()
            finish()
        }
    }


}