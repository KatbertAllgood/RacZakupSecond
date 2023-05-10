package com.example.data.mapper

import com.example.data.models.packs.RacParamsData
import com.example.domain.models.packs.RacParamsDomain

class RacParamsToDomain(
    private val racParamsData: RacParamsData
) {
    fun toDomain() = RacParamsDomain(
        racParamsData.fats,
        racParamsData.proteins,
        racParamsData.carbohydrates,
        racParamsData.energyValue,
    )
}