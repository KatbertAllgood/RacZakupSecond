package com.example.data.models.shop

import com.google.gson.annotations.SerializedName

data class ProductParamsData(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("categoryId")
    val categoryId: Int = 0,
    @SerializedName("manufacturer")
    val manufacturer: String = "",
    @SerializedName("brand")
    val brand: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("fraction")
    val fraction: String = "",
    @SerializedName("weigh")
    val weigh: String = "",
    @SerializedName("pricePerKgMl")
    val pricePerKgMl: Double = 0.0,
    @SerializedName("composition")
    val composition: String = "",
    @SerializedName("energyValue")
    val energyValue: Double = 0.0,
    @SerializedName("fats")
    val fats: Double = 0.0,
    @SerializedName("proteins")
    val proteins: Double = 0.0,
    @SerializedName("carbohydrates")
    val carbohydrates: Double = 0.0,
    @SerializedName("storageConditions")
    val storageConditions: String = "",
    @SerializedName("storageTemperatureMin")
    val storageTemperatureMin: Int = 0,
    @SerializedName("storageTemperatureMax")
    val storageTemperatureMax: Int = 0,
    @SerializedName("storageLife")
    val storageLife: Int = 0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("racCoefficientBenefit")
    val racCoefficientBenefit: Double = 0.0,
    @SerializedName("racCategoryBenefit")
    val racCategoryBenefit: String = "",
    @SerializedName("shopId")
    val shopId: Int = 0
)
