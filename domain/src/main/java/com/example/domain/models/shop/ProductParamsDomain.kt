package com.example.domain.models.shop

data class ProductParamsDomain(
    val title: String = "",
    val price: Int = 0,
    val categoryId: Int = 0,
    val manufacturer: String = "",
    val brand: String = "",
    val country: String = "",
    val fraction: String = "",
    val weigh: String = "",
    val pricePerKgMl: Int = 0,
    val composition: String = "",
    val energyValue: Int = 0,
    val fats: Int = 0,
    val proteins: Int = 0,
    val carbohydrates: Int = 0,
    val storageConditions: String = "",
    val storageTemperatureMin: Int = 0,
    val storageTemperatureMax: Int = 0,
    val storageLife: Int = 0,
    val description: String = "",
    val image: String = "",
    val racCoefficientBenefit: Int = 0,
    val racCategoryBenefit: String = "",
    val shopId: Int = 0
)
