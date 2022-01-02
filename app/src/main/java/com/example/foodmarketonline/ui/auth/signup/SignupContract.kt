package com.example.foodmarketonline.ui.auth.signup

import android.net.Uri
import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView
import com.example.foodmarketonline.model.reponse.login.LoginResponse
import com.example.foodmarketonline.model.request.RegisterRequest

interface SignupContract {

    interface View : BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhotoRegister(filePath:Uri, view: android.view.View)
    }

}