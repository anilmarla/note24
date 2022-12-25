package com.anil.notes24.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anil.notes24.model.Note
import com.anil.notes24.repository.NotesRepository

class MainViewModel : ViewModel() {
    var notes: MutableLiveData<List<Note>> = MutableLiveData()
    var notesRepository: NotesRepository = NotesRepository()

    fun loadNotes() {
        notes.postValue(notesRepository.getNotes())
    }
}