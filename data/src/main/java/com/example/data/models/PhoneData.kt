package com.example.data.models

import com.google.gson.annotations.SerializedName

data class PhoneData(
    @SerializedName("phone")
    val phoneNumber: String = ""
)
