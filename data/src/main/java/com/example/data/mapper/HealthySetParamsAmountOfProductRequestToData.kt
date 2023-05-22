package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAmountOfProductRequestData
import com.example.domain.models.packs.HealthySetParamsAmountOfProductRequestDomain

class HealthySetParamsAmountOfProductRequestToData(
    private val healthySetParamsAmountOfProductRequestDomain: HealthySetParamsAmountOfProductRequestDomain
) {
    fun toData() = HealthySetParamsAmountOfProductRequestData(
        healthySetParamsAmountOfProductRequestDomain.amount
    )
}