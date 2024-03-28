package com.example.alica_app.data.models

import kotlinx.serialization.Serializable


@Serializable
data class ResponseAuthentication(
    var token:String,
    val type:String,
    var email:String?,
    var id: String?,
    var refreshToken:String,
    val role:List<Role>
) {}