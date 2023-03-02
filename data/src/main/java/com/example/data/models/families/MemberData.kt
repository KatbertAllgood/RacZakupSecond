package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class MemberData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("isUser")
    val isUser: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("weight")
    val weight: Double = 0.0,
    @SerializedName("birthday")
    val birthday: String = "",
    @SerializedName("gender")
    val gender: String = "",

    @SerializedName("age")
    val age: Int = 0,
    @SerializedName("preferences")
    var preferences: List<Int> = listOf(),
    @SerializedName("position")
    var position: Int = 0
)
