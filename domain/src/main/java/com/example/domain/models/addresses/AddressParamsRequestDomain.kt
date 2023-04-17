package com.example.domain.models.addresses

data class AddressParamsRequestDomain(
    var name: String = "",
    var country: String = "",
    var region: String = "",
    var district: String = "",
    var city: String = "",
    var locality: String = "",
    var street: String = "",
    var house_number: String = "",
    var corpus: String = "",
    var apartment: String = "",
    var entrance: String = "",
    var floor: String = "",
    var comment: String = "",
    var postal_code: String = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0
)