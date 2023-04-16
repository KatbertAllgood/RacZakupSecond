package com.example.data.models.addresses

import com.google.gson.annotations.SerializedName

data class LocationParamsData(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("coordinates")
    val coordinates: List<Double> = listOf()
)
