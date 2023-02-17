package com.anil.notes24.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.anil.notes24.MyApplication
import com.anil.notes24.base.BaseRepository
import com.anil.notes24.database.AppDatabase
import com.anil.notes24.database.NoteDao
import com.anil.notes24.database.UserDao
import com.anil.notes24.model.Note
import com.anil.notes24.network.Api
import com.anil.notes24.network.ApiService
import com.anil.notes24.network.Result
import com.anil.notes24.network.request.CreateToDoRequest
import com.anil.notes24.network.responseData

class NotesRepository(private val application: Application): BaseRepository() {

    private val notesDao: NoteDao = AppDatabase.getDatabase(application).noteDao()
    private val apiService: ApiService = Api.getInstance(application.applicationContext).apiService

    fun getNotes(): LiveData<List<Note>> {
        return notesDao.getAll()
    }

    fun insert(note: Note) {
        notesDao.insert(note)
    }

    fun update(note: Note){
        notesDao.update(note)
    }

    fun delete(note: Note){
        notesDao.deleteNote(note)
    }

    fun deleteAll(){
        notesDao.deleteAll()
    }

    suspend fun addToDo(payload: CreateToDoRequest): Result<Note> {
        val result = getResult { apiService.addToDo(payload) }

        result.responseData?.let{
            notesDao.insert(note = it)

           /* val preferences = application.getSharedPreferences("userId", Context.MODE_PRIVATE)
            preferences.edit().apply {
                it?.let {
                    putInt("userId", it.userId)
                }

            }*/

        }

        return result

    }
}