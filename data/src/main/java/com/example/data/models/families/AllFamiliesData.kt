package com.example.data.models.families

import com.google.gson.annotations.SerializedName

data class AllFamiliesData(
    @SerializedName("results")
    val results: List<NewFamilyData> = listOf(),
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0,
    @SerializedName("offset")
    val offset: Int = 0
)
