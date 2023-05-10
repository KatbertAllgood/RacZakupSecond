package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsData
import com.example.data.models.packs.HealthySetParamsResponseData
import com.example.domain.models.packs.HealthySetParamsResponseDomain

class HealthySetParamsResponseToData(
    private val healthySetParamsResponseDomain: HealthySetParamsResponseDomain
) {
    fun toData() = HealthySetParamsResponseData(
        healthySetParamsResponseDomain.status,
        HealthySetParamsToData(healthySetParamsResponseDomain.data).toData()
    )
}