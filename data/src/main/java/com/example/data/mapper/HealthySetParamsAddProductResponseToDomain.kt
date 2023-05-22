package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAddProductResponseData
import com.example.domain.models.packs.HealthySetParamsAddProductResponseDomain

class HealthySetParamsAddProductResponseToDomain(
    private val healthySetParamsAddProductResponseData: HealthySetParamsAddProductResponseData
) {
    fun toDomain() = HealthySetParamsAddProductResponseDomain(
        healthySetParamsAddProductResponseData.status,
        HealthySetParamsAddedProductToDomain(healthySetParamsAddProductResponseData.data).toDomain()
    )
}