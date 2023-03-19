package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class FamilyData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("members")
    var members: List<MemberData> = listOf()

//    @SerializedName("checked")
//    var checked: Boolean = false //  ?????????
)
