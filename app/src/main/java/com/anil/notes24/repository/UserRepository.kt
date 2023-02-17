package com.anil.notes24.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.anil.notes24.base.BaseRepository
import com.anil.notes24.database.AppDatabase
import com.anil.notes24.database.UserDao
import com.anil.notes24.model.Note
import com.anil.notes24.model.User
import com.anil.notes24.network.Api
import com.anil.notes24.network.ApiService
import com.anil.notes24.network.Result
import com.anil.notes24.network.request.CreateToDoRequest
import com.anil.notes24.network.request.LoginRequest
import com.anil.notes24.network.responseData


class UserRepository(private val application: Application) : BaseRepository() {

    private val userDao: UserDao = AppDatabase.getDatabase(application).userDao()
    private val apiService: ApiService = Api.getInstance(application.applicationContext).apiService


    fun deleteAll() {
        userDao.deleteAll()
    }

    fun insert(user: User) {
        userDao.insert(user)
    }

    fun getUser(): LiveData<User> {
        return userDao.getUser()
    }

    fun isLoggedInUser(): LiveData<User> {
        return userDao.isLoggedInUser()
    }

    suspend fun login(payload: LoginRequest): Result<User> {
        val result = getResult { apiService.login(payload) }

        result.responseData?.let {
            // delete all users
            userDao.deleteAll()

            // save user in db
            it.isLoggedIn = true
            userDao.insert(it)

            // save user token in preferences
            val preferences = application.getSharedPreferences("todos", Context.MODE_PRIVATE)
            preferences.edit().apply {
                putString("token", it.token)
                apply()
            }
        }
        return result
    }

    /*fun getUser(): LiveData<User> {
        return userDao.getUser()
    }*/




}