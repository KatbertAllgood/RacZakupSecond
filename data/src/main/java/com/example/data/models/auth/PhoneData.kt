package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class PhoneData(
    @SerializedName("phone")
    val phoneNumber: String = ""
)
