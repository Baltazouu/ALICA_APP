package com.example.alica_app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SignUpBody(

    @SerialName("firstName")
    val firstName: String,

    @SerialName("lastName")
    val lastName: String,

    @SerialName("email")
    val emailAddress: String,

    @SerialName("password")
    val password: String) {
    override fun toString(): String {
        return "SignUpBody(firstName='$firstName', lastName='$lastName', emailAddress='$emailAddress', password='$password')"
    }
}