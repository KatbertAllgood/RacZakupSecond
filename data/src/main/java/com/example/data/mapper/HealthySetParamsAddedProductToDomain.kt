package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAddedProductData
import com.example.domain.models.packs.HealthySetParamsAddedProductDomain

class HealthySetParamsAddedProductToDomain(
    private val healthySetParamsAddedProductData: HealthySetParamsAddedProductData
) {
    fun toDomain() = HealthySetParamsAddedProductDomain(
        healthySetParamsAddedProductData.healthySetId,
        ProductParamsToDomain(healthySetParamsAddedProductData.addProduct).toDomain(),
        RacParamsToDomain(healthySetParamsAddedProductData.racParams).toDomain(),
    )
}