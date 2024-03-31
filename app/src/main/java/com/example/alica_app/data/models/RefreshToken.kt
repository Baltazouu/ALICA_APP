package com.example.alica_app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    val refreshToken:String) {}