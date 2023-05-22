package com.example.data.models.packs

import com.google.gson.annotations.SerializedName

data class HealthySetParamsAddProductResponseData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("data")
    val data: HealthySetParamsAddedProductData = HealthySetParamsAddedProductData()
)
