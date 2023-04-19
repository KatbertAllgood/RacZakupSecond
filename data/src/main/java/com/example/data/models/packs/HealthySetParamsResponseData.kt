package com.example.data.models.packs

import com.example.data.models.shop.ProductParamsData
import com.google.gson.annotations.SerializedName

data class HealthySetParamsResponseData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("data")
    val data: List<ProductParamsData> = listOf()
)
