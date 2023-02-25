package com.example.data.models

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("authId")
    val authId: String = "",
    @SerializedName("role")
    val role: String = "",
    @SerializedName("userId")
    val userId: String = "",
    @SerializedName("iat")
    val iat: Int = 0,
    @SerializedName("exp")
    val exp: Int = 0
)
