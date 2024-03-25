package com.example.alica_app.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ResponseAuthentication(
    val token:String,
    val type:String,
    val email:String,
    val id: String,
    val refreshToken:String,
    val role:List<Role>
) {}