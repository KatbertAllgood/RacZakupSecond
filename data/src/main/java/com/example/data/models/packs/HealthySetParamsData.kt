package com.example.data.models.packs

import com.google.gson.annotations.SerializedName

data class HealthySetParamsData(
    @SerializedName("addressId")
    var addressId: Int = 0,
    @SerializedName("familyId")
    var familyId: Int = 0,
    @SerializedName("budget")
    var budget: Int = 0,
    @SerializedName("days")
    var days: Int = 0,
    @SerializedName("shop")
    var shop: Int = 0
)
