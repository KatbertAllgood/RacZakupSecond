package com.example.domain.models.families

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewMemberUpdateDomain(
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var birthday: String = "",
    var gender: String = ""
) : Parcelable
