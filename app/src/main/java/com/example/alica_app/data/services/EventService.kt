package com.example.alica_app.data.services


import com.example.alica_app.constants.Constants
import com.example.alica_app.data.models.Event
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

val httpEventClient = OkHttpClient()
interface EventService {
    @Headers("Content-Type: application/json")

    @GET("events/{id}")
    fun getEvent(@Path("id") id:String,
                  @Header("Authorization") token: String): Call<Event>


    @PUT("events/{id}")
    fun subscriber( @Path("id") id:String, nbRegistration : Int,
                    @Header("Authorization") token: String) : Call<Event>

    @PUT("events/{id}")
    fun unsubscriber( @Path("id") id:String, nbRegistration: Int,
                    @Header("Authorization") token: String) : Call<Event>
}

fun createEventRetrofit() : Retrofit =
    Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(httpEventClient)
        .build()