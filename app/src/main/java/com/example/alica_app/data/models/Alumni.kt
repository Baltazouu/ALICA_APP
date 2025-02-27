package com.example.alica_app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Alumni(
    val id: String,
    val email: String,
    val role: String? = null,
    var entryYear: String? = null,
    val firstName: String,
    val lastName: String,
    var linkedinURL: String? = null,
    var githubURL: String? = null,
    var portfolioURL: String? = null,
    val imageId: String? = null,

    @SerialName("_links")
    val links: Links
)

@Serializable
data class Links(
    val self: Link,
    val offers: Link,
    val events: Link,
    val formations: Link
)

@Serializable
data class Link(
    val href: String
)
