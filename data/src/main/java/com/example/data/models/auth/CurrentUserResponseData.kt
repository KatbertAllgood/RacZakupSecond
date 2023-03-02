package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class CurrentUserResponseData(
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("user")
    val user: UserData
)
