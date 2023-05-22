package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAddProductResponseData
import com.example.domain.models.packs.HealthySetParamsAddProductResponseDomain

class HealthySetParamsAddProductResponseToData(
    private val healthySetParamsAddProductResponseDomain: HealthySetParamsAddProductResponseDomain
) {
    fun toData() = HealthySetParamsAddProductResponseData(
        healthySetParamsAddProductResponseDomain.status,
        HealthySetParamsAddedProductToData(healthySetParamsAddProductResponseDomain.data).toData()
    )
}