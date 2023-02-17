package com.anil.notes24.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey var id: Int,
    var username: String? = null,
    var email: String? = null,
    var firstname: String? = null,
    var lastname: String? = null,
    var gender: String? = null,
    var image: String? = null,
    var isLoggedIn: Boolean = false,
    var token: String? = null
)