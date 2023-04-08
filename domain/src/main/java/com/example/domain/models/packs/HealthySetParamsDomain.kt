package com.example.domain.models.packs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthySetParamsDomain(
    var addressId: Int = 0,
    var familyId: Int = 0,
    var budget: Int = 0,
    var days: Int = 0,
    var shop: Int = 0
) : Parcelable
