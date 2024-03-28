package com.example.alica_app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val id : String ? = null,
    val alumniId:String,
    val title:String,
    var startDate:String,
    var endDate:String,
    val companyName:String,
    val isCurrent:Boolean?,
) {}