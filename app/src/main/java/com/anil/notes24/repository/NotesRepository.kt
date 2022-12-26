package com.anil.notes24.repository

import androidx.lifecycle.LiveData
import com.anil.notes24.database.NoteDao
import com.anil.notes24.model.Note

class NotesRepository(private val notesDao: NoteDao) {

    /**
     * Get all notes from database
     * */
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
}