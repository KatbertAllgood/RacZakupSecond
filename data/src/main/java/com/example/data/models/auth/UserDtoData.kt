package com.example.data.models.auth

import com.google.gson.annotations.SerializedName

data class UserDtoData(
    @SerializedName("userDto")
    val userDto: UserData
)
