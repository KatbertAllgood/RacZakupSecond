package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAddedProductData
import com.example.domain.models.packs.HealthySetParamsAddedProductDomain

class HealthySetParamsAddedProductToData(
    private val healthySetParamsAddedProductDomain: HealthySetParamsAddedProductDomain
) {
    fun toData() = HealthySetParamsAddedProductData(
        healthySetParamsAddedProductDomain.healthySetId,
        ProductParamsToData(healthySetParamsAddedProductDomain.addProduct).toData(),
        RacParamsToData(healthySetParamsAddedProductDomain.racParams).toData(),
    )
}