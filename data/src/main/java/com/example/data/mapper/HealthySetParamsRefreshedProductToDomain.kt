package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRefreshedProductData
import com.example.domain.models.packs.HealthySetParamsRefreshedProductDomain

class HealthySetParamsRefreshedProductToDomain(
    private val healthySetParamsRefreshedProductData : HealthySetParamsRefreshedProductData
) {
    fun toDomain() = HealthySetParamsRefreshedProductDomain(
        healthySetParamsRefreshedProductData.healthySetId,
        ProductParamsToDomain(
            healthySetParamsRefreshedProductData.refreshProduct
        ).toDomain()
    )
}