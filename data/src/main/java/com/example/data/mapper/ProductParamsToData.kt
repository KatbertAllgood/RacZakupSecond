package com.example.data.mapper

import com.example.data.models.shop.ProductParamsData
import com.example.domain.models.shop.ProductParamsDomain

class ProductParamsToData(
    private val productParamsDomain: ProductParamsDomain
) {
    fun toData() = ProductParamsData(
        productParamsDomain.title,
        productParamsDomain.price,
        productParamsDomain.categoryId,
        productParamsDomain.manufacturer,
        productParamsDomain.brand,
        productParamsDomain.country,
        productParamsDomain.fraction,
        productParamsDomain.weigh,
        productParamsDomain.pricePerKgMl,
        productParamsDomain.composition,
        productParamsDomain.energyValue,
        productParamsDomain.fats,
        productParamsDomain.proteins,
        productParamsDomain.carbohydrates,
        productParamsDomain.storageConditions,
        productParamsDomain.storageTemperatureMin,
        productParamsDomain.storageTemperatureMax,
        productParamsDomain.storageLife,
        productParamsDomain.description,
        productParamsDomain.image,
        productParamsDomain.racCoefficientBenefit,
        productParamsDomain.racCategoryBenefit,
        productParamsDomain.shopId,
    )
}