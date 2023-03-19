package com.example.domain.models.families

data class FamilyDomain(
    val id: Int = 0,
    var name: String = "",
    var members: List<MemberDomain> = listOf()
//    var checked: Boolean = false //  ?????????
) {
//    init {
//        members = List<MemberDomain>()
//    }
}
