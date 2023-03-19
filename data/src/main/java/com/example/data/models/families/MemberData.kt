package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class MemberData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("isUser")
    val isUser: Boolean = false,
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

    @SerializedName("age")
    var age: Int = 0,
    @SerializedName("preferences")
    var preferences: List<Int> = listOf(),
    @SerializedName("position")
    var position: Int = 0
)
