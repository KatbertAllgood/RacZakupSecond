package com.example.data.mapper

import com.example.data.models.packs.HealthySetParamsResponseData
import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.packs.HealthySetParamsResponseDomain
import com.example.domain.models.shop.ProductParamsDomain

class HealthySetParamsResponseToDomain(
    private val healthySetParamsResponseData: HealthySetParamsResponseData
) {
    val data: MutableList<ProductParamsDomain> = mutableListOf()

    init {
        for (i in healthySetParamsResponseData.data) {
            data.add(ProductParamsToDomain(i).toDomain())
        }
    }

    fun toDomain() = HealthySetParamsResponseDomain(
        healthySetParamsResponseData.status,
        data
    )
}