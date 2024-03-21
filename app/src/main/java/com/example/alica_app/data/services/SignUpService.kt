package com.example.alica_app.data.services

import com.example.alica_app.constants.Constants
import com.example.alica_app.data.models.SignUpBody
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

val httpClient = OkHttpClient()

interface SignUpService {
    @POST("auth/signUp")
    fun signUp(@Body signUpBody: SignUpBody): Call<Unit>
}


fun createSignUpRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(httpClient)
        .build()