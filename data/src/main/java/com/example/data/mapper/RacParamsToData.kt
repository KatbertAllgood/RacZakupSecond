package com.example.data.mapper

import com.example.data.models.packs.RacParamsData
import com.example.domain.models.packs.RacParamsDomain

class RacParamsToData(
    private val racParamsDomain: RacParamsDomain
) {
    fun toData() = RacParamsData(
        racParamsDomain.fats,
        racParamsDomain.proteins,
        racParamsDomain.carbohydrates,
        racParamsDomain.energyValue,
    )
}