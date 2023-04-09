package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class NewFamilyUpdateData(
    @SerializedName("name")
    var name: String = ""
)
