package com.example.data.models.geo

import com.google.gson.annotations.SerializedName

data class RequestQueryData(
    @SerializedName("addressQuery")
    val addressQuery: String = ""
)
