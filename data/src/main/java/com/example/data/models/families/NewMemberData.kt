package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class NewMemberData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("weight")
    var weight: Int = 0,
    @SerializedName("birthday")
    var birthday: String = "",
    @SerializedName("gender")
    var gender: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = ""
)
