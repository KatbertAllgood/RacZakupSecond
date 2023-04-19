package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsResponseData
import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.packs.HealthySetParamsResponseDomain

class HealthySetParamsResponseToData(
    private val healthySetParamsResponseDomain: HealthySetParamsResponseDomain
) {
    val data: MutableList<ProductParamsData> = mutableListOf()

    init {
        for (i in healthySetParamsResponseDomain.data) {
            data.add(ProductParamsToData(i).toData())
        }
    }

    fun toData() = HealthySetParamsResponseData(
        healthySetParamsResponseDomain.status,
        data
    )
}