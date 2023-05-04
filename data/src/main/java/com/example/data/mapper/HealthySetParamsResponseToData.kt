package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsGroupsData
import com.example.data.models.packs.HealthySetParamsResponseData
import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.packs.HealthySetParamsResponseDomain

class HealthySetParamsResponseToData(
    private val healthySetParamsResponseDomain: HealthySetParamsResponseDomain
) {
    val data: MutableList<HealthySetParamsGroupsData> = mutableListOf()

    init {
        for (i in healthySetParamsResponseDomain.data) {
            data.add(HealthySetParamsGroupsToData(i).toData())
        }
    }

    fun toData() = HealthySetParamsResponseData(
        healthySetParamsResponseDomain.status,
        data
    )
}