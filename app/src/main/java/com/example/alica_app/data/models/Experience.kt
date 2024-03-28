package com.example.alica_app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val id : String,
    val alumniId:String,
    val title:String,
    val startDate:String,
    val endDate:String,
    val companyName:String,
    val current:Boolean,
) {}