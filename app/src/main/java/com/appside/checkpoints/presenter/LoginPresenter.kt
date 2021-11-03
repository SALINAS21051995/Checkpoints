package com.appside.checkpoints.presenter

import com.appside.checkpoints.R
import com.appside.checkpoints.contracts.LoginContract
import com.appside.checkpoints.model.LoginForm

class LoginPresenter(var view: LoginContract.View): LoginContract.Presenter {
    override fun validateInputs(loginForm: LoginForm): Boolean {
        if(loginForm.number.isBlank() && loginForm.password.isBlank()) { view.openOptionDialog(); return false}
        if(loginForm.number.isBlank()) { view.showMessage(view.getContext().getString(R.string.login_user_number_error)); return false }
        if(loginForm.password.isBlank()) { view.showMessage(view.getContext().getString(R.string.login_user_password_error)); return false }
        return true
    }

}