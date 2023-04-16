package com.example.data.mapper

import com.example.data.models.addresses.LocationParamsData
import com.example.domain.models.addresses.LocationParamsDomain

class LocationParamsToDomain(
    private val locationParamsData: LocationParamsData
) {
    fun toDomain() = LocationParamsDomain(
        locationParamsData.type,
        locationParamsData.coordinates
    )
}