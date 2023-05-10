package com.example.domain.models.packs

import com.example.domain.models.shop.ProductParamsDomain

data class HealthySetParamsAndGroupsDomain(
    val racParams: RacParamsDomain = RacParamsDomain(),
    val racEnergy: List<ProductParamsDomain> = listOf(),
    val racPower: List<ProductParamsDomain> = listOf(),
    val racOil: List<ProductParamsDomain> = listOf(),
    val racOther: List<ProductParamsDomain> = listOf()
)
