package com.example.data.models.packs

import com.example.data.models.shop.ProductParamsData
import com.google.gson.annotations.SerializedName

data class HealthySetParamsAndGroupsData(
    @SerializedName("racParams")
    val racParams: RacParamsData = RacParamsData(),
    @SerializedName("racEnergy")
    val racEnergy: List<ProductParamsData> = listOf(),
    @SerializedName("racPower")
    val racPower: List<ProductParamsData> = listOf(),
    @SerializedName("racOil")
    val racOil: List<ProductParamsData> = listOf(),
    @SerializedName("racOther")
    val racOther: List<ProductParamsData> = listOf()
)
