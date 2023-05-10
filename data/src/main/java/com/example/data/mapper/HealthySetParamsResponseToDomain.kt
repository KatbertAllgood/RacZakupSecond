package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsResponseData
import com.example.domain.models.packs.HealthySetParamsDomain
import com.example.domain.models.packs.HealthySetParamsResponseDomain

class HealthySetParamsResponseToDomain(
    private val healthySetParamsResponseData: HealthySetParamsResponseData
) {
    fun toDomain() = HealthySetParamsResponseDomain(
        healthySetParamsResponseData.status,
        HealthySetParamsToDomain(healthySetParamsResponseData.data).toDomain()
    )
}