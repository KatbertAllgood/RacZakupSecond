package com.example.data.models.geo

import com.example.data.mapper.ServerResponseToData
import com.google.gson.annotations.SerializedName

data class ResponseGeoData(
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("result")
    val result: String = "",
    @SerializedName("postal_code")
    val postal_code: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("federal_district")
    val federal_district: String = "",
    @SerializedName("region_type_full")
    val region_type_full: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("area")
    val area: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("city_area")
    val city_area: String = "",
    @SerializedName("city_district")
    val city_district: String = "",
    @SerializedName("settlement_type_full")
    val settlement_type_full: String = "",
    @SerializedName("settlement")
    val settlement: String = "",
    @SerializedName("street")
    val street: String = "",
    @SerializedName("stead_type_full")
    val stead_type_full: String = "",
    @SerializedName("stead")
    val stead: String = "",
    @SerializedName("house")
    val house: String = "",
    @SerializedName("block")
    val block: String = ""
)
