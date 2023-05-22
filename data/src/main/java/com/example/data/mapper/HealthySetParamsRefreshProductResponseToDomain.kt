package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRefreshProductResponseData
import com.example.domain.models.packs.HealthySetParamsRefreshProductResponseDomain

class HealthySetParamsRefreshProductResponseToDomain(
    private val healthySetParamsRefreshProductResponseData: HealthySetParamsRefreshProductResponseData
) {
    fun toDomain() = HealthySetParamsRefreshProductResponseDomain(
        healthySetParamsRefreshProductResponseData.status,
        HealthySetParamsRefreshedProductToDomain(
            healthySetParamsRefreshProductResponseData.data
        ).toDomain()
    )
}