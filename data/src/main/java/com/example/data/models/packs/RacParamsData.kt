package com.example.data.models.packs

import com.google.gson.annotations.SerializedName

data class RacParamsData(
    @SerializedName("fats")
    val fats: Int = 0,
    @SerializedName("proteins")
    val proteins: Int = 0,
    @SerializedName("carbohydrates")
    val carbohydrates: Int = 0,
    @SerializedName("energyValue")
    val energyValue: Int = 0
)
