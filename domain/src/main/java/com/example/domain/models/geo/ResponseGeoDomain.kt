package com.example.domain.models.geo

data class ResponseGeoDomain(
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val result: String? = "",
    val postal_code: String? = "",
    val country: String? = "",
    val federal_district: String? = "",
    val region_type_full: String? = "",
    val region: String? = "",
    val area: String? = "",
    val city: String? = "",
    val city_area: String? = "",
    val city_district: String? = "",
    val settlement_type_full: String? = "",
    val settlement: String? = "",
    val street: String? = "",
    val stead_type_full: String? = "",
    val stead: String? = "",
    val house: String? = "",
    val block: String? = ""
)
