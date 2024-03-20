package com.example.alica_app.data.models

import kotlinx.serialization.Serializable

@Serializable
class SignUpBody(val firstName: String, val lastName: String, val emailAddress: String, val password: String) {
    override fun toString(): String {
        return "SignUpBody(firstName='$firstName', lastName='$lastName', emailAddress='$emailAddress', password='$password')"
    }
}