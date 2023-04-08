package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsData
import com.example.domain.models.packs.HealthySetParamsDomain

class HealthySetParamsToData(
    private val healthySetParamsDomain: HealthySetParamsDomain
) {
    fun toData() = HealthySetParamsData(
        healthySetParamsDomain.addressId,
        healthySetParamsDomain.familyId,
        healthySetParamsDomain.budget,
        healthySetParamsDomain.days,
        healthySetParamsDomain.shop
    )
}