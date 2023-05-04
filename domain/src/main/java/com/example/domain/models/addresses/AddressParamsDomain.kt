package com.example.domain.models.addresses

data class AddressParamsDomain(

    val id: Int = 0,
    val user_id: String = "",
    var name: String = "",
    var country: String? = "",
    var federal_district: String? = "",
    var region: String? = "",
    var region_type_full: String? = "",
    var city: String = "",
    var city_type_full: String? = "",
    var city_area: String? = "",
    var settlement: String? = "",
    var settlement_type_full: String? = "",
    var street: String = "",
    var street_type_full: String? = "",
    var house: String = "",
    var house_type_full: String? = "",
    var block: String? = "",
    var block_type_full: String? = "",
    var flat: String = "",
    var entrance: String? = "",
    var floor: String? = "",
    var beltway_hit: String = "",
    var comment: String? = "",
    var postal_code: String = "",
    val location: LocationParamsDomain = LocationParamsDomain(),
    val createdDate: String? = "",
    val updatedDate: String? = "",
    val version: Int = 0

)
