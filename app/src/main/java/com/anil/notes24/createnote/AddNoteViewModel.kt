package com.anil.notes24.createnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.anil.notes24.model.Note
import com.anil.notes24.model.User
import com.anil.notes24.repository.NotesRepository
import com.anil.notes24.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository
    private var userRepository: UserRepository

    var user: LiveData<User>

    init {
        notesRepository = NotesRepository(application)
        userRepository = UserRepository(application)
        user = userRepository.getUser()
    }

    fun addNote(todo: String, title: String) {
        val n = Note(
            id = UUID.randomUUID().toString(),
            title = title,
            note = todo,
            createdAt = Date().time,

            )

        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insert(n)
        }
    }

    fun updateNote(note: Note?) {
        note?.let {
            viewModelScope.launch(Dispatchers.IO) {
                notesRepository.update(note)
            }
        }
    }

    fun deleteNote(note: Note?) {
        note?.let {
            viewModelScope.launch(Dispatchers.IO) {
                notesRepository.delete(note)
            }
        }
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUser()
        }
    }
}