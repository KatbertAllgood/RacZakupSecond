package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsGroupsData
import com.example.domain.models.packs.HealthySetParamsGroupsDomain
import com.example.domain.models.shop.ProductParamsDomain

class HealthySetParamsGroupsToDomain(
    private val healthySetParamsGroupsData: HealthySetParamsGroupsData
) {
    private val racEnergy: MutableList<ProductParamsDomain> = mutableListOf()
    private val racPower: MutableList<ProductParamsDomain> = mutableListOf()
    private val racOil: MutableList<ProductParamsDomain> = mutableListOf()
    private val racOther: MutableList<ProductParamsDomain> = mutableListOf()

    init {
        for (i in healthySetParamsGroupsData.racEnergy) {
            racEnergy.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsGroupsData.racPower) {
            racPower.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsGroupsData.racOil) {
            racOil.add(ProductParamsToDomain(i).toDomain())
        }
        for (i in healthySetParamsGroupsData.racOther) {
            racOther.add(ProductParamsToDomain(i).toDomain())
        }
    }

    fun toDomain() = HealthySetParamsGroupsDomain(
        racEnergy,
        racPower,
        racOil,
        racOther
    )
}