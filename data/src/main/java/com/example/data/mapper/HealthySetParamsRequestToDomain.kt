package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRequestData
import com.example.domain.models.packs.HealthySetParamsRequestDomain

class HealthySetParamsRequestToDomain(
    private val healthySetParamsRequestData: HealthySetParamsRequestData
) {
    fun toDomain() = HealthySetParamsRequestDomain(
        healthySetParamsRequestData.addressId,
        healthySetParamsRequestData.familyId,
        healthySetParamsRequestData.budget,
        healthySetParamsRequestData.days,
        healthySetParamsRequestData.shop
    )
}