package com.example.data.models

import com.google.gson.annotations.SerializedName

data class ServerResponseData(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("message")
    val message: String = ""
)
