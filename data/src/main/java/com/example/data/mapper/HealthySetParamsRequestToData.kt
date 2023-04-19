package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRequestData
import com.example.domain.models.packs.HealthySetParamsRequestDomain

class HealthySetParamsRequestToData(
    private val healthySetParamsRequestDomain: HealthySetParamsRequestDomain
) {
    fun toData() = HealthySetParamsRequestData(
        healthySetParamsRequestDomain.addressId,
        healthySetParamsRequestDomain.familyId,
        healthySetParamsRequestDomain.budget,
        healthySetParamsRequestDomain.days,
        healthySetParamsRequestDomain.shop
    )
}