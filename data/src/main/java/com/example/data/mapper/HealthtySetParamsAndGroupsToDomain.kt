package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAndGroupsData
import com.example.domain.models.packs.HealthySetParamsAndGroupsDomain
import com.example.domain.models.shop.ProductParamsDomain

class HealthtySetParamsAndGroupsToDomain(
    private val healthySetParamsAndGroupsData: HealthySetParamsAndGroupsData
) {
    private val racParams = RacParamsToDomain(healthySetParamsAndGroupsData.racParams).toDomain()

    private val racEnergy: MutableList<ProductParamsDomain> = mutableListOf()
    private val racPower: MutableList<ProductParamsDomain> = mutableListOf()
    private val racOil: MutableList<ProductParamsDomain> = mutableListOf()
    private val racOther: MutableList<ProductParamsDomain> = mutableListOf()

    init {
        for (i in healthySetParamsAndGroupsData.racEnergy) {
            racEnergy.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsAndGroupsData.racPower) {
            racPower.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsAndGroupsData.racOil) {
            racOil.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsAndGroupsData.racOther) {
            racOther.add(ProductParamsToDomain(i).toDomain())
        }
    }

    fun toDomain() = HealthySetParamsAndGroupsDomain(
        racParams,
        racEnergy,
        racPower,
        racOil,
        racOther
    )
}