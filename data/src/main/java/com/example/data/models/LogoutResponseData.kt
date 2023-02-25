package com.example.data.models

import com.google.gson.annotations.SerializedName

data class LogoutResponseData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = ""
)
