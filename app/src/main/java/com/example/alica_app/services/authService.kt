package com.example.alica_app.services

import com.example.alica_app.constants.Constants
import com.example.alica_app.model.Alumni
import com.example.alica_app.model.ResponseAuthentication
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST


interface authService{

    @POST("/auth/signUp")
    fun signUp(alumni: Alumni)

    @POST("/auth/signIn")
    fun signIn(email:String,password:String) : Call<ResponseAuthentication>

}

var retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .build()
