package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class FamilyData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("members")
    val members: List<MemberData>

//    @SerializedName("checked")
//    var checked: Boolean = false //  ?????????
)
