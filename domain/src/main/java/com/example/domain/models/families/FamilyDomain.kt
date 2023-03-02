package com.example.domain.models.families

data class FamilyDomain(
    val id: Int = 0,
    val name: String = "",
    val members: List<MemberDomain>
//    var checked: Boolean = false //  ?????????
)
