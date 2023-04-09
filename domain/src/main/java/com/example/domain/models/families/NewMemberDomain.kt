package com.example.domain.models.families

data class NewMemberDomain(
    val id: Int = 0,
    var name: String = "",
    var height: Int = 0,
    var weight: Int = 0,
    var birthday: String = "",
    var gender: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val family: NewFamilyDomain = NewFamilyDomain()
)
