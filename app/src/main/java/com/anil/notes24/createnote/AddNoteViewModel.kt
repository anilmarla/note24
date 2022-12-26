package com.anil.notes24.createnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anil.notes24.database.AppDatabase
import com.anil.notes24.model.Note
import com.anil.notes24.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository

    init {
        val notesDao = AppDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(notesDao)
    }

    fun addNote(note: String) {
        val n = Note(
            id = UUID.randomUUID().toString(),
            note = note,
            createdAt = Date().time
        )
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insert(n)
        }
    }
}