package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsGroupsData
import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.packs.HealthySetParamsGroupsDomain

class HealthySetParamsGroupsToData(
    private val healthySetParamsGroupsDomain : HealthySetParamsGroupsDomain
) {
    private val racEnergy: MutableList<ProductParamsData> = mutableListOf()
    private val racPower: MutableList<ProductParamsData> = mutableListOf()
    private val racOil: MutableList<ProductParamsData> = mutableListOf()
    private val racOther: MutableList<ProductParamsData> = mutableListOf()

    init {
        for (i in healthySetParamsGroupsDomain.racEnergy) {
            racEnergy.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsGroupsDomain.racPower) {
            racPower.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsGroupsDomain.racOil) {
            racOil.add(ProductParamsToData(i).toData())
        }
        for (i in healthySetParamsGroupsDomain.racOther) {
            racOther.add(ProductParamsToData(i).toData())
        }
    }

    fun toData() = HealthySetParamsGroupsData(
        racEnergy,
        racPower,
        racOil,
        racOther
    )
}