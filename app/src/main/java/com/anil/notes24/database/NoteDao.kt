package com.anil.notes24.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.anil.notes24.model.Note

@Dao
interface NoteDao {
    // get all notes
    @Query("Select * from notes")
    fun getAll(): LiveData<List<Note>>

    // insert note
    @Insert
    fun insert(note: Note)

    // delete note
    @Delete
    fun deleteNote(note: Note)
}