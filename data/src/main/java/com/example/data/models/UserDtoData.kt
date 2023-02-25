package com.example.data.models

import com.google.gson.annotations.SerializedName

data class UserDtoData(
    @SerializedName("userDto")
    val userDto: UserData
)
