package com.anil.notes24.repository

import com.anil.notes24.model.Note
import java.util.*

class NotesRepository {
    private val notes: MutableList<Note> = mutableListOf()

    fun getNotes() : List<Note>{
        // clear all notes first
        notes.clear()

        notes.add(Note("1", "Buy Groceries", Date()))
        notes.add(Note("2", "Call mom!", Date()))
        notes.add(Note("3", "Account Details: 2949400303033, IFSC2003SBI", Date()))

        return notes
    }
}