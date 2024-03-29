package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsData
import com.example.domain.models.packs.HealthySetParamsDomain

class HealthySetParamsToDomain(
    private val healthySetParamsData: HealthySetParamsData
) {
    fun toDomain() = HealthySetParamsDomain(
        healthySetParamsData.healthySetId,
        HealthtySetParamsAndGroupsToDomain(healthySetParamsData.healthySet).toDomain()
    )
}