package com.example.alica_app.data.services

import com.example.alica_app.constants.Constants
import com.example.alica_app.data.models.Experience
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ExperiencesService {
    @Headers("Content-Type: application/json")

    @POST("experiences")
    suspend fun addExperience(@Header("Authorization") token: String, @Body experience: Experience): Experience

    @GET("experiences/alumni")
    fun findAlumniExperiences(@Header("Authorization") token: String): Call<List<Experience>>

    @DELETE("experiences/{id}")
    suspend fun deleteExperience(@Header("Authorization") token:String,@Path("id") id:String) : Response<Void>

}
fun createExperienceRetrofit(): Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(httpClient)
        .addConverterFactory(
            Json{ this.ignoreUnknownKeys=true }.asConverterFactory("application/json".toMediaType()))
        .build()
