package com.example.data.models

import com.google.gson.annotations.SerializedName

data class CodeData(
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("code")
    val code: String = ""
)
