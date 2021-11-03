package com.appside.checkpoints.contracts

import android.content.Context
import com.appside.checkpoints.model.LoginForm

interface LoginContract {
    interface View{
        fun showMessage(message: String)
        fun openOptionDialog()
        fun getContext(): Context
    }
    interface Presenter{
        fun validateInputs(loginForm: LoginForm): Boolean
    }
}