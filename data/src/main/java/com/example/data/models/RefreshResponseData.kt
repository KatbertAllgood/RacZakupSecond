package com.example.data.models

import com.google.gson.annotations.SerializedName

data class RefreshResponseData(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("data")
    val data: UserDtoData,
    @SerializedName("accessToken")
    val accessToken: String = "",
    @SerializedName("refreshToken")
    val refreshToken: String = ""
)
