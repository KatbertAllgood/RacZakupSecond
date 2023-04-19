package com.example.data.mapper

import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.shop.ProductParamsDomain

class ProductParamsToDomain(
    private val productParamsData: ProductParamsData
) {
    fun toDomain() = ProductParamsDomain(
        productParamsData.title,
        productParamsData.price,
        productParamsData.categoryId,
        productParamsData.manufacturer,
        productParamsData.brand,
        productParamsData.country,
        productParamsData.fraction,
        productParamsData.weigh,
        productParamsData.pricePerKgMl,
        productParamsData.composition,
        productParamsData.energyValue,
        productParamsData.fats,
        productParamsData.proteins,
        productParamsData.carbohydrates,
        productParamsData.storageConditions,
        productParamsData.storageTemperatureMin,
        productParamsData.storageTemperatureMax,
        productParamsData.storageLife,
        productParamsData.description,
        productParamsData.image,
        productParamsData.racCoefficientBenefit,
        productParamsData.racCategoryBenefit,
        productParamsData.shopId,
    )
}