package com.example.alica_app

import android.app.Application
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.createAuthenticationRetrofit

class Application : Application() {


    override fun onCreate() {
        super.onCreate()
        authenticationService = createAuthenticationRetrofit().create(AuthenticationService::class.java)
    }

    lateinit var authenticationService : AuthenticationService

}