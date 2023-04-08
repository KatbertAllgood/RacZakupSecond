package com.example.domain.models.addresses

data class AddressParamsDomain(
    val id: Int = 0,
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var street: String = "",
    var building: Int = 0,
    var flat: Int = 0,
    var floor: Int = 0,
    var entrance: Int = 0
)
