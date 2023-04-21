package com.example.data.models.packs

import com.google.gson.annotations.SerializedName

data class HealthySetParamsRequestData(
    @SerializedName("addressId")
    var addressId: Int = 0,
    @SerializedName("familyId")
    var familyId: Int = 0,
    @SerializedName("budget")
    var budget: String = "",
    @SerializedName("days")
    var days: Int = 0,
//    @SerializedName("shop")
//    var shop: Int = 0
)
