package com.example.domain.models.geo

data class ResponseGeoCoordinatesDomain(
    val value: String = "",
    val unrestricted_value: String = "",
    val data: ResponseGeoDomain = ResponseGeoDomain()
)
