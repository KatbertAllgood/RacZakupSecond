package com.example.data.models.addresses

import com.google.gson.annotations.SerializedName

data class AddressParamsData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("latitude")
    var latitude: Double = 0.0,
    @SerializedName("longitude")
    var longitude: Double = 0.0,
    @SerializedName("street")
    var street: String = "",
    @SerializedName("building")
    var building: Int = 0,
    @SerializedName("flat")
    var flat: Int = 0,
    @SerializedName("floor")
    var floor: Int = 0,
    @SerializedName("entrance")
    var entrance: Int = 0

)
