package com.example.domain.models.addresses

data class LocationParamsDomain(
    val type: String = "",
    val coordinates: List<Double> = listOf()
)
