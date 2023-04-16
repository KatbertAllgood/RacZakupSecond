package com.example.data.models.addresses

import com.google.gson.annotations.SerializedName

data class AddressParamsData(

    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("user_id")
    val user_id: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("region")
    var region: String? = "",
    @SerializedName("district")
    var district: String? = "",
    @SerializedName("city")
    var city: String? = "",
    @SerializedName("locality")
    var locality: String? = "",
    @SerializedName("street")
    var street: String? = "",
    @SerializedName("house_number")
    var house_number: String? = "",
    @SerializedName("corpus")
    var corpus: String? = "",
    @SerializedName("apartment")
    var apartment: String? = "",
    @SerializedName("entrance")
    var entrance: String? = "",
    @SerializedName("floor")
    var floor: String? = "",
    @SerializedName("comment")
    var comment: String? = "",
    @SerializedName("postal_code")
    var postal_code: String? = "",
    @SerializedName("location")
    val location: LocationParamsData = LocationParamsData(),
    @SerializedName("createdDate")
    val createdDate: String? = "",
    @SerializedName("updatedDate")
    val updatedDate: String? = "",
    @SerializedName("version")
    val version: Int = 0

//    @SerializedName("lat")
//    var lat: Double = 0.0,
//    @SerializedName("lon")
//    var lon: Double = 0.0,

)
