package com.example.data.models.geo

import com.google.gson.annotations.SerializedName

data class RequestCoordinatesData(
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lon")
    val lon: Double = 0.0
)
