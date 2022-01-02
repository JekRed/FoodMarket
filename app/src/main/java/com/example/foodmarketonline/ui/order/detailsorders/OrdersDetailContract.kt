package com.example.foodmarketonline.ui.order.detailsorders


import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView

interface OrdersDetailContract {
    interface View : BaseView {
        fun onUpdateTransactionSuccess(message: String)
        fun onUpdateTransactionFailed(message: String)
    }

    interface Presenter : OrdersDetailContract, BasePresenter {
        fun getUpdateTransaction(id:String, status:String)
    }
}