package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRefreshedProductData
import com.example.domain.models.packs.HealthySetParamsRefreshedProductDomain

class HealthySetParamsRefreshedProductToData(
    private val healthySetParamsRefreshedProductDomain : HealthySetParamsRefreshedProductDomain
) {
    fun toData() = HealthySetParamsRefreshedProductData(
        healthySetParamsRefreshedProductDomain.healthySetId,
        ProductParamsToData(
            healthySetParamsRefreshedProductDomain.refreshProduct
        ).toData()
    )
}