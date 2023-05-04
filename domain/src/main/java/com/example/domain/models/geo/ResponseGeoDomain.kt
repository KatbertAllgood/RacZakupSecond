package com.example.domain.models.geo

data class ResponseGeoDomain(
    val result: String? = "",
    val country: String? = "",
    val federal_district: String? = "",
    val region: String? = "",
    val region_type_full: String? = "",
    val city: String? = "",
    val city_type_full: String? = "",
    val city_area: String? = "",
    val settlement: String? = "",
    val settlement_type_full: String? = "",
    val street: String? = "",
    val street_type_full: String? = "",
    val house: String? = "",
    val house_type_full: String? = "",
    val block: String? = "",
    val block_type_full: String? = "",
    val beltway_hit: String? = "",
    val postal_code: String? = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
)
