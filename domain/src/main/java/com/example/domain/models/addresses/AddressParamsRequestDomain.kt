package com.example.domain.models.addresses

data class AddressParamsRequestDomain(
    var name: String? = "",
    var country: String? = "", // not necessary
    var federal_district: String? = "", // not necessary
    var region: String? = "", // not necessary
    var region_type_full: String? = "", // not necessary
    var city: String? = "",
    var city_type_full: String? = "", // not necessary
    var city_area: String? = "", // not necessary
    var settlement: String? = "", // not necessary
    var settlement_type_full: String? = "", // not necessary
    var street: String? = "",
    var street_type_full: String? = "", // not necessary
    var house: String? = "",
    var house_type_full: String? = "", // not necessary
    var block: String? = "", // not necessary
    var block_type_full: String? = "", // not necessary
    var flat: String? = "",
    var entrance: String? = "", // not necessary
    var floor: String? = "", // not necessary
    var beltway_hit: String? = "",
    var comment: String? = "", // not necessary
    var postal_code: String? = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0
)
