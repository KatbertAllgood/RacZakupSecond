package com.example.domain.models.families

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberDomain(
    val id: Int = 0,
    val isUser: Boolean = false,
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var birthday: String = "",
    var gender: String = "",

    var age: Int = 0,
    var preferences: List<Int> = listOf(),
    var position: Int = 0
) : Parcelable
