package com.example.data.models.packs

import com.google.gson.annotations.SerializedName

data class HealthySetParamsData(
    @SerializedName("helthySetId")
    val healthySetId: Int = 0,
    @SerializedName("helthySet")
    val healthySet: HealthySetParamsAndGroupsData = HealthySetParamsAndGroupsData()
)
