package com.example.foodmarketonline.network

import com.example.foodmarketonline.model.reponse.ProfileResponse
import com.example.foodmarketonline.model.reponse.transaction.TransactionResponse
import com.example.foodmarketonline.model.reponse.Wrapper
import com.example.foodmarketonline.model.reponse.checkout.CheckoutResponse
import com.example.foodmarketonline.model.reponse.home.HomeResponse
import com.example.foodmarketonline.model.reponse.login.LoginResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


interface EndPoint {

    @FormUrlEncoded
    @POST("login")
    fun login(@Field("email") email: String,
              @Field("password") password: String): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(@Field("name") name: String,
                 @Field("email") email: String,
                 @Field("password") password: String,
                 @Field("password_confirmation") password_confirmation: String,
                 @Field("address") address: String,
                 @Field("city") city: String,
                 @Field("houseNumber") houseNumber: String,
                 @Field("phoneNumber") phoneNumber: String): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(@Part profileImage: MultipartBody.Part): Observable<Wrapper<Any>>

    @GET("user")
    fun profile(): Observable<Wrapper<ProfileResponse>>

    @GET("food")
    fun home(): Observable<Wrapper<HomeResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(@Field("food_id") food_id: String,
                 @Field("user_id") user_id: String,
                 @Field("quantity") quantity: String,
                 @Field("total") total: String,
                 @Field("status") status: String): Observable<Wrapper<CheckoutResponse>>

    @GET("transaction")
    fun transaction(): Observable<Wrapper<TransactionResponse>>

    @FormUrlEncoded
    @POST("transaction/{id}")
    fun transactionUpdate(@Path(value = "id") userId:String,
                          @Field("status") status: String): Observable<Wrapper<Any>>
}