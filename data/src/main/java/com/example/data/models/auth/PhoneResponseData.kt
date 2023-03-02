package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class PhoneResponseData(
    @SerializedName("status")
    val status: Int = 0
)
