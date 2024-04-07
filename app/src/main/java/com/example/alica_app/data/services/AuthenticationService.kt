package com.example.alica_app.data.services

import com.example.alica_app.constants.Constants
import com.example.alica_app.data.models.RefreshToken
import com.example.alica_app.data.models.ResponseAuthentication
import com.example.alica_app.data.models.SignInBody
import com.example.alica_app.data.models.SignUpBody
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.Headers
import java.util.UUID

val httpClient = OkHttpClient()

interface AuthenticationService {

    @Headers("Content-Type: application/json")

    @POST("auth/signUp")
    fun signUp(@Body signUpBody: SignUpBody): Call<Unit>

    @POST("auth/signIn")
    suspend fun signIn(@Body signInBody: SignInBody): ResponseAuthentication
    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshToken: RefreshToken): ResponseAuthentication
}

fun createAuthenticationRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(httpClient)
        .build()