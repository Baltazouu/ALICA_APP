package com.example.alica_app.model

data class ResponseAuthentication(
    val token:String,
    val type:String,
    val email:String,
    val id: String,
    val refreshToken:String,
    ) {}