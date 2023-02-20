package com.anil.notes24.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.notes24.base.SingleLiveEvent
import com.anil.notes24.model.Note
import com.anil.notes24.network.Result
import com.anil.notes24.network.request.CreateToDoRequest
import com.anil.notes24.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var notesRepository: NotesRepository
    var notes: LiveData<List<Note>> = MutableLiveData()

    private val _note = SingleLiveEvent<Result<Note>>()

    val note: LiveData<Result<Note>>
        get() = _note

    init {
       notesRepository = NotesRepository(application)
        notes = notesRepository.getNotes()
    }

    fun addToDo(todo: String, userId: Int, completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _note.postValue(Result.Loading)

            val payload = CreateToDoRequest(
                todo = todo, userId = userId, completed = true
            )
            _note.postValue(notesRepository.addToDo(payload ))

        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAll()
        }
    }


}