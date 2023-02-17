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
    var todo: String? = null,
    var title: String? = null,
    var note: String? = null,
    var completed: Boolean? = null,
    var userId: Int? = null,
    val createdAt: Long? = null
) : Parcelable