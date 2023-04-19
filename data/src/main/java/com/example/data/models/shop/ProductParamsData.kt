package com.example.data.models.shop

import com.google.gson.annotations.SerializedName

data class ProductParamsData(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("price")
    val price: Int = 0,
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
    val pricePerKgMl: Int = 0,
    @SerializedName("composition")
    val composition: String = "",
    @SerializedName("energyValue")
    val energyValue: Int = 0,
    @SerializedName("fats")
    val fats: Int = 0,
    @SerializedName("proteins")
    val proteins: Int = 0,
    @SerializedName("carbohydrates")
    val carbohydrates: Int = 0,
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
    val racCoefficientBenefit: Int = 0,
    @SerializedName("racCategoryBenefit")
    val racCategoryBenefit: String = "",
    @SerializedName("shopId")
    val shopId: Int = 0
)
