package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class LogoutResponseData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = ""
)
