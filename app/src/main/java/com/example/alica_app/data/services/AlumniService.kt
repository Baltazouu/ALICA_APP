package com.example.alica_app.data.services

import com.example.alica_app.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import com.example.alica_app.data.models.Alumni
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
    @PUT("alumnis/{id}")
    fun createAlumni(@Path("id") id:String,
                     @Body alumni: Alumni,
                     @Header("Authorization") token : String): Call<Alumni>

}


fun createAlumniRetrofit() : Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()