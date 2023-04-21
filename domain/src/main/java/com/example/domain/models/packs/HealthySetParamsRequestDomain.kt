package com.example.domain.models.packs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HealthySetParamsRequestDomain(
    var addressId: Int = 0,
    var familyId: Int = 0,
    var budget: String = "",
    var days: Int = 0,
//    var shop: Int = 0
) : Parcelable
