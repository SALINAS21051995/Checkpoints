package com.appside.checkpoints.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appside.checkpoints.model.LoginForm

class LoginViewModel(var loginData: LoginForm): ViewModel()

class LoginViewModelFactory(private val loginData: LoginForm): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(loginData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

