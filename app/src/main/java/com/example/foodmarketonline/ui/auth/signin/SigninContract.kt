package com.example.foodmarketonline.ui.auth.signin

import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView
import com.example.foodmarketonline.model.reponse.login.LoginResponse

interface SigninContract {

    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }

}