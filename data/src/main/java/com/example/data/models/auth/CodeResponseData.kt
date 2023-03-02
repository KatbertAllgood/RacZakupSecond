package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class CodeResponseData(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = "",
    @SerializedName("user")
    val user: UserData
)
