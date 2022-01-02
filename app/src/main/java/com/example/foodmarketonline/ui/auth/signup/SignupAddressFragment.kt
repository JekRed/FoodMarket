package com.example.foodmarketonline.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodmarketonline.FoodMarket
import com.example.foodmarketonline.R
import com.example.foodmarketonline.model.reponse.login.LoginResponse
import com.example.foodmarketonline.model.request.RegisterRequest
import com.example.foodmarketonline.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_address.*


class SignupAddressFragment : Fragment(), SignupContract.View {

    lateinit var data:RegisterRequest
    lateinit var presenter : SignupPresenter
    private var progressDialog: Dialog? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    // Masuk melalui Button Signup to Contineu Address to Success
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

//        initDummy()
        initView()
        initListener()
    }

    private fun initListener() {
        btnSignUpNow.setOnClickListener { view ->

            val phone = etPhoneNumber.text.toString()
            val address = etAddress.text.toString()
            val houseNumber = etHouseNumber.text.toString()
            val city = etCity.text.toString()

            //===============
//            presenter.submitPhotoRegister(data.filePath!!, view)
            //===============

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            when {
                phone.isEmpty() -> {
                    etPhoneNumber.error = "Silahkan masukkan nomor handphone"
                    etPhoneNumber.requestFocus()
                }
                address.isEmpty() -> {
                    etAddress.error = "Silahkan masukkan alamat"
                    etAddress.requestFocus()
                }
                houseNumber.isEmpty() -> {
                    etHouseNumber.error = "Silahkan masukkan no rumah"
                    etHouseNumber.requestFocus()
                }
                city.isEmpty() -> {
                    etCity.error = "Silahkan masukkan kota"
                    etCity.requestFocus()
    //            } else if (data.filePath == null) {
    //                presenter.submitRegister(data, view)

                }
                else -> {
                    presenter.submitRegister(data, view)
                }
            }
        }
    }

//    private fun initDummy(){
//        etPhoneNumber.setText("085692284842")
//        etAddress.setText("Jl. Tanah Baru")
//        etHouseNumber.setText("110")
//        etCity.setText("Depok")
//    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if(data.filePath == null) {
            Navigation.findNavController(view).navigate(R.id.action_fragmentSignUpAddress_to_fragmentSignSuccess)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view : View) {
        Navigation.findNavController(view).navigate(R.id.action_fragmentSignUpAddress_to_fragmentSignSuccess)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}