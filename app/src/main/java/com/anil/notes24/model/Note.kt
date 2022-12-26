package com.anil.notes24.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// @Annotation
@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey val id: String,
    var title: String,
    var note: String,
    val createdAt: Long
) : Parcelable