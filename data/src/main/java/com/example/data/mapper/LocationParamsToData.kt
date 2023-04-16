package com.example.data.mapper

import com.example.data.models.addresses.LocationParamsData
import com.example.domain.models.addresses.LocationParamsDomain

class LocationParamsToData(
    private val locationParamsDomain: LocationParamsDomain
) {
    fun toData() = LocationParamsData(
        locationParamsDomain.type,
        locationParamsDomain.coordinates
    )
}