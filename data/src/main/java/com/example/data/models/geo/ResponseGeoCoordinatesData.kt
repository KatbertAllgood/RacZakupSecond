package com.example.data.models.geo

import com.google.gson.annotations.SerializedName

data class ResponseGeoCoordinatesData(
    @SerializedName("value")
    val value: String = "",
    @SerializedName("unrestricted_value")
    val unrestricted_value: String = "",
    @SerializedName("data")
    val data: ResponseGeoData = ResponseGeoData()
)
