package com.example.domain.models.packs

import com.example.domain.models.shop.ProductParamsDomain

data class HealthySetParamsAddedProductDomain(
    val healthySetId: Int = 0,
    val addProduct: ProductParamsDomain = ProductParamsDomain(),
    val racParams: RacParamsDomain = RacParamsDomain()
)
