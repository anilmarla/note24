package com.anil.notes24.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anil.notes24.MyApplication
import com.anil.notes24.database.AppDatabase
import com.anil.notes24.model.Note
import com.anil.notes24.repository.NotesRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository
    var notes: LiveData<List<Note>> = MutableLiveData()

    init {
        val notesDao = AppDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(notesDao)
        notes = notesRepository.getNotes()
    }

    fun loadNotes() {
        //notes.postValue(notesRepository.getNotes())
    }
}