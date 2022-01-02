package com.example.foodmarketonline.ui.home

import android.net.Uri
import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView
import com.example.foodmarketonline.model.reponse.home.HomeResponse
import com.example.foodmarketonline.model.reponse.login.LoginResponse
import com.example.foodmarketonline.model.request.RegisterRequest

interface HomeContract {

    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }

}