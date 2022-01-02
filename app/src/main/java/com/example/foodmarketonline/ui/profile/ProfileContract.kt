package com.example.foodmarketonline.ui.profile

import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView
import com.example.foodmarketonline.model.reponse.ProfileResponse

interface ProfileContract {
    interface View : BaseView {
        fun onProfileSuccess(profileResponse: ProfileResponse)
        fun onProfileFailed(message: String)
    }

    interface Presenter : ProfileContract, BasePresenter {
        fun getProfile()
    }
}