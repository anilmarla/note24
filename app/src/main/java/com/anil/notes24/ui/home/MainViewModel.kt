package com.anil.notes24.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.notes24.database.AppDatabase
import com.anil.notes24.model.Note
import com.anil.notes24.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository
    var notes: LiveData<List<Note>> = MutableLiveData()

    init {
        val notesDao = AppDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(notesDao)
        notes = notesRepository.getNotes()
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAll()
        }
    }
}