package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class NewFamilyData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("user_id")
    val user_id: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("members")
    var members: List<NewMemberData> = listOf()
)
