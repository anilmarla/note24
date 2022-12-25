package com.anil.notes24.model

import java.util.Date

data class Note(
    val id: String,
    val note: String,
    val createdAt: Date
)