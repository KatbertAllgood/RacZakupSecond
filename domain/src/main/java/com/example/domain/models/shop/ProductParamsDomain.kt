package com.example.domain.models.shop

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductParamsDomain(
    val title: String = "",
    val price: Double = 0.0,
    val categoryId: Int = 0,
    val manufacturer: String = "",
    val brand: String = "",
    val country: String = "",
    val fraction: String = "",
    val weigh: String = "",
    val pricePerKgMl: Double = 0.0,
    val composition: String = "",
    val energyValue: Double = 0.0,
    val fats: Double = 0.0,
    val proteins: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val storageConditions: String = "",
    val storageTemperatureMin: Int = 0,
    val storageTemperatureMax: Int = 0,
    val storageLife: Int = 0,
    val description: String = "",
    val image: String = "",
    val racCoefficientBenefit: Double = 0.0,
    val racCategoryBenefit: String = "",
    val shopId: Int = 0
) : Parcelable
