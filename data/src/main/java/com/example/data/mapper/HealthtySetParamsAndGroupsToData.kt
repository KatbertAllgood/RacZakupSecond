package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsAndGroupsData
import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.packs.HealthySetParamsAndGroupsDomain
import com.example.domain.models.shop.ProductParamsDomain

class HealthtySetParamsAndGroupsToData(
    private val healthySetParamsAndGroupsDomain: HealthySetParamsAndGroupsDomain
) {
    private val racParams = RacParamsToData(healthySetParamsAndGroupsDomain.racParams).toData()

    private val racEnergy: MutableList<ProductParamsData> = mutableListOf()
    private val racPower: MutableList<ProductParamsData> = mutableListOf()
    private val racOil: MutableList<ProductParamsData> = mutableListOf()
    private val racOther: MutableList<ProductParamsData> = mutableListOf()

    init {
        for (i in healthySetParamsAndGroupsDomain.racEnergy) {
            racEnergy.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsAndGroupsDomain.racPower) {
            racPower.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsAndGroupsDomain.racOil) {
            racOil.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsAndGroupsDomain.racOther) {
            racOther.add(ProductParamsToData(i).toData())
        }
    }

    fun toData() = HealthySetParamsAndGroupsData(
        racParams,
        racEnergy,
        racPower,
        racOil,
        racOther
    )
}