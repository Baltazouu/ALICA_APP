package com.example.alica_app.data.services

import com.example.alica_app.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Headers
import com.example.alica_app.data.models.Alumni
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path


val okHttpClient = OkHttpClient()

interface AlumniService {


    @Headers("Content-Type: application/json")

    @GET("alumnis/{id}")
    fun getAlumni(@Path("id") id:String,
                  @Header("Authorization") token: String): Call<Alumni>
    @PUT("alumnis") // identified by token
    fun createAlumni(//@Path("id") id:String,
                     @Body alumni: Alumni,
                     @Header("Authorization") token : String): Call<Alumni>

}


fun createAlumniRetrofit() : Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(Json{ this.ignoreUnknownKeys=true }
        .asConverterFactory("application/json".toMediaType()))
        .build()
