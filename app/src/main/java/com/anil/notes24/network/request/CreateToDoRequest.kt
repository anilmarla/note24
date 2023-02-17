package com.anil.notes24.network.request

data class CreateToDoRequest(
    val todo: String,
    val completed: Boolean,
    val userId: Int
)