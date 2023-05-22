package com.example.data.models.packs

import com.example.data.models.shop.ProductParamsData
import com.google.gson.annotations.SerializedName

data class HealthySetParamsRefreshedProductData(
    @SerializedName("helthySetId")
    val healthySetId: Int = 0,
    @SerializedName("refreshProduct")
    val refreshProduct: ProductParamsData = ProductParamsData()
)
