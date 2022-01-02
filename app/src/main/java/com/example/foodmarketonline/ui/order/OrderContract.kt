package com.example.foodmarketonline.ui.order

import com.example.foodmarketonline.model.reponse.transaction.TransactionResponse
import com.example.foodmarketonline.base.BasePresenter
import com.example.foodmarketonline.base.BaseView

interface OrderContract {

    interface View : BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }

}