package com.example.data.models.addresses

import com.google.gson.annotations.SerializedName

data class AddressParamsData(

    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("user_id")
    val user_id: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("federal_district")
    var federal_district: String? = "",
    @SerializedName("region")
    var region: String? = "",
    @SerializedName("region_type_full")
    var region_type_full: String? = "",
    @SerializedName("city")
    var city: String = "",
    @SerializedName("city_type_full")
    var city_type_full: String? = "",
    @SerializedName("city_area")
    var city_area: String? = "",
    @SerializedName("settlement")
    var settlement: String? = "",
    @SerializedName("settlement_type_full")
    var settlement_type_full: String? = "",
    @SerializedName("street")
    var street: String = "",
    @SerializedName("street_type_full")
    var street_type_full: String? = "",
    @SerializedName("house")
    var house: String = "",
    @SerializedName("house_type_full")
    var house_type_full: String? = "",
    @SerializedName("block")
    var block: String? = "",
    @SerializedName("block_type_full")
    var block_type_full: String? = "",
    @SerializedName("flat")
    var flat: String = "",
    @SerializedName("entrance")
    var entrance: String? = "",
    @SerializedName("floor")
    var floor: String? = "",
    @SerializedName("beltway_hit")
    var beltway_hit: String = "",
    @SerializedName("comment")
    var comment: String? = "",
    @SerializedName("postal_code")
    var postal_code: String = "",
    @SerializedName("location")
    val location: LocationParamsData = LocationParamsData(),
    @SerializedName("createdDate")
    val createdDate: String? = "",
    @SerializedName("updatedDate")
    val updatedDate: String? = "",
    @SerializedName("version")
    val version: Int = 0
)
