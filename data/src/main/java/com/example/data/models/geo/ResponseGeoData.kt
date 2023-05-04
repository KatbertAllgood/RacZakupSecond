package com.example.data.models.geo

import com.example.data.mapper.ServerResponseToData
import com.google.gson.annotations.SerializedName

data class ResponseGeoData(
    @SerializedName("result")
    val result: String? = "",
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("federal_district")
    val federal_district: String? = "",
    @SerializedName("region")
    val region: String? = "",
    @SerializedName("region_type_full")
    val region_type_full: String? = "",
    @SerializedName("city")
    val city: String? = "",
    @SerializedName("city_type_full")
    val city_type_full: String? = "",
    @SerializedName("city_area")
    val city_area: String? = "",
    @SerializedName("settlement")
    val settlement: String? = "",
    @SerializedName("settlement_type_full")
    val settlement_type_full: String? = "",
    @SerializedName("street")
    val street: String? = "",
    @SerializedName("street_type_full")
    val street_type_full: String? = "",
    @SerializedName("house")
    val house: String? = "",
    @SerializedName("house_type_full")
    val house_type_full: String? = "",
    @SerializedName("block")
    val block: String? = "",
    @SerializedName("block_type_full")
    val block_type_full: String? = "",
    @SerializedName("beltway_hit")
    val beltway_hit: String? = "",
    @SerializedName("postal_code")
    val postal_code: String? = "",
    @SerializedName("geo_lat")
    val lat: Double = 0.0,
    @SerializedName("geo_lon")
    val lon: Double = 0.0,
)
