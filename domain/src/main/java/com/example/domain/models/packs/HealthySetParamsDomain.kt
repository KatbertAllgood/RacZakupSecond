package com.example.domain.models.packs

import com.example.domain.models.shop.ProductParamsDomain

data class HealthySetParamsDomain(
    val healthySetId: Int = 0,
    val healthySet: HealthySetParamsAndGroupsDomain = HealthySetParamsAndGroupsDomain()
)
