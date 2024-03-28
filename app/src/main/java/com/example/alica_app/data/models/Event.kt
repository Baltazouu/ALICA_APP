package com.example.alica_app.data.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Date


@Serializable
data class Event(
    val id: String,
    val organizerId: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    @Contextual
    val date: Date,
    val nbMaxRegistrations: Int,
    val nbRegistrations: Int,
    @SerialName("_links")
    val links: Links
) {
    override fun toString(): String {
        return "Event(Title='$title', description='$description', date='$date', nombre d'inscrit='$nbRegistrations'/'$nbMaxRegistrations')"
    }
}
