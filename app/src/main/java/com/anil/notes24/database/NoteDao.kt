package com.anil.notes24.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.notes24.model.Note

@Dao
interface NoteDao {
    // get all notes
    @Query("Select * from notes order by createdAt DESC")
    fun getAll(): LiveData<List<Note>>

    // insert note
    @Insert
    fun insert(note: Note)

    // updating note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    // delete note
    @Delete
    fun deleteNote(note: Note)

    // delete all notes
    @Query("DELETE from notes")
    fun deleteAll()
}