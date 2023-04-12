package com.example.domain.models.addresses

data class AddressParamsDomain(

    val id: Int = 0,
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
    var lat: Float = 0F,
    var lon: Float = 0F,

)
