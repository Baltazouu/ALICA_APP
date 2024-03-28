package com.example.alica_app

import android.app.Application
import com.example.alica_app.data.services.AlumniService
import com.example.alica_app.data.services.AuthenticationService
import com.example.alica_app.data.services.ExperiencesService
import com.example.alica_app.data.services.createAlumniRetrofit
import com.example.alica_app.data.services.createAuthenticationRetrofit
import com.example.alica_app.data.services.createExperienceRetrofit

class Application : Application() {


    override fun onCreate() {
        super.onCreate()
        authenticationService = createAuthenticationRetrofit().create(AuthenticationService::class.java)
        alumniService = createAlumniRetrofit().create(AlumniService::class.java)
        experiencesService = createExperienceRetrofit().create(ExperiencesService::class.java)
    }

    lateinit var authenticationService : AuthenticationService

    lateinit var alumniService : AlumniService

    lateinit var experiencesService : ExperiencesService

}