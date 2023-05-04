package com.example.domain.models.packs

import com.example.domain.models.shop.ProductParamsDomain

data class HealthySetParamsResponseDomain(
    val status: String = "",
    val data: List<HealthySetParamsGroupsDomain> = listOf()
)
