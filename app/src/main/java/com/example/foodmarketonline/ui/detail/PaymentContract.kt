package com.example.foodmarketonline.ui.detail

import android.net.Uri
import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView
import com.example.foodmarketonline.model.reponse.checkout.CheckoutResponse
import com.example.foodmarketonline.model.reponse.home.HomeResponse
import com.example.foodmarketonline.model.reponse.login.LoginResponse
import com.example.foodmarketonline.model.request.RegisterRequest

interface PaymentContract {

    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId:String, userId:String, quantity:String, total:String, view: android.view.View)
    }

}