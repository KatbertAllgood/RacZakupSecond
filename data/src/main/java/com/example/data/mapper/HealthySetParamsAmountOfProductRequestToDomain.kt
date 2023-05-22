package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAmountOfProductRequestData
import com.example.domain.models.packs.HealthySetParamsAmountOfProductRequestDomain

class HealthySetParamsAmountOfProductRequestToDomain(
    private val healthySetParamsAmountOfProductRequestData: HealthySetParamsAmountOfProductRequestData
) {
    fun toDomain() = HealthySetParamsAmountOfProductRequestDomain(
        healthySetParamsAmountOfProductRequestData.amount
    )
}