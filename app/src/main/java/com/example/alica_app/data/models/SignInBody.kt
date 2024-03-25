package com.example.alica_app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SignInBody(
    val email: String,
    val password: String


) {
    override fun toString(): String {
        return "SignInBody(email='$email', password='$password')"
    }
}