package com.anil.notes24.network

import com.anil.notes24.model.Note
import com.anil.notes24.model.User
import com.anil.notes24.network.request.CreateToDoRequest
import com.anil.notes24.network.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body payload: LoginRequest
    ): Response<User>

    @POST("todos/add")
    suspend fun addToDo(
        @Body payload: CreateToDoRequest
    ): Response<Note>


}