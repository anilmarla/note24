package com.anil.notes24.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anil.notes24.base.SingleLiveEvent
import com.anil.notes24.model.User
import com.anil.notes24.network.Result
import com.anil.notes24.network.request.LoginRequest
import com.anil.notes24.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var userRepository: UserRepository
    var loggedInUser: LiveData<User> = MutableLiveData()

    private val _user = SingleLiveEvent<Result<User>>()

    val user: LiveData<Result<User>>
        get() = _user

    init {
        userRepository = UserRepository(application)
        loggedInUser = userRepository.isLoggedInUser()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _user.postValue(Result.Loading)

            val payload = LoginRequest(
                username = username,
                password = password
            )
            _user.postValue(userRepository.login(payload))
        }
    }

    fun delete(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteAll()

        }
    }
    fun insert(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

    fun getUser(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUser()
        }
    }

}