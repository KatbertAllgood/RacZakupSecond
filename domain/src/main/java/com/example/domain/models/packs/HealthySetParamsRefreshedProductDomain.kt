package com.example.domain.models.packs

import com.example.domain.models.shop.ProductParamsDomain

data class HealthySetParamsRefreshedProductDomain(
    val healthySetId: Int = 0,
    val refreshProduct: ProductParamsDomain = ProductParamsDomain(),
    val racParams: RacParamsDomain = RacParamsDomain()
)
