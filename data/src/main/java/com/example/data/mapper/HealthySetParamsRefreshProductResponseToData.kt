package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsRefreshProductResponseData
import com.example.domain.models.packs.HealthySetParamsRefreshProductResponseDomain

class HealthySetParamsRefreshProductResponseToData(
    private val healthySetParamsRefreshProductResponseDomain: HealthySetParamsRefreshProductResponseDomain
) {
    fun toData() = HealthySetParamsRefreshProductResponseData(
        healthySetParamsRefreshProductResponseDomain.status,
        HealthySetParamsRefreshedProductToData(
            healthySetParamsRefreshProductResponseDomain.data
        ).toData()
    )
}