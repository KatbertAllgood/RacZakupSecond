package com.example.data.models.packs

import com.example.data.models.shop.ProductParamsData
import com.google.gson.annotations.SerializedName

data class HealthySetParamsAddedProductData(
    @SerializedName("helthySetId")
    val healthySetId: Int = 0,
    @SerializedName("addProduct")
    val addProduct: ProductParamsData = ProductParamsData(),
    @SerializedName("racParams")
    val racParams: RacParamsData = RacParamsData()
)
