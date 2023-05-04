package com.example.data.models.addresses

import com.google.gson.annotations.SerializedName

data class AddressParamsRequestData(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("country")
    var country: String? = "", // not necessary
    @SerializedName("federal_district")
    var federal_district: String? = "", // not necessary
    @SerializedName("region")
    var region: String? = "", // not necessary
    @SerializedName("region_type_full")
    var region_type_full: String? = "", // not necessary
    @SerializedName("city")
    var city: String? = "",
    @SerializedName("city_type_full")
    var city_type_full: String? = "", // not necessary
    @SerializedName("city_area")
    var city_area: String? = "", // not necessary
    @SerializedName("settlement")
    var settlement: String? = "", // not necessary
    @SerializedName("settlement_type_full")
    var settlement_type_full: String? = "", // not necessary
    @SerializedName("street")
    var street: String? = "",
    @SerializedName("street_type_full")
    var street_type_full: String? = "", // not necessary
    @SerializedName("house")
    var house: String? = "",
    @SerializedName("house_type_full")
    var house_type_full: String? = "", // not necessary
    @SerializedName("block")
    var block: String? = "", // not necessary
    @SerializedName("block_type_full")
    var block_type_full: String? = "", // not necessary
    @SerializedName("flat")
    var flat: String? = "",
    @SerializedName("entrance")
    var entrance: String? = "", // not necessary
    @SerializedName("floor")
    var floor: String? = "", // not necessary
    @SerializedName("beltway_hit")
    var beltway_hit: String? = "",
    @SerializedName("comment")
    var comment: String? = "", // not necessary
    @SerializedName("postal_code")
    var postal_code: String? = "",
    @SerializedName("lat")
    var lat: Double = 0.0,
    @SerializedName("lon")
    var lon: Double = 0.0
)
