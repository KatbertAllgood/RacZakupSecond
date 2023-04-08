package com.example.domain.models.families

data class NewFamilyDomain(
    val id: Int = 0,
    var name: String = "",
    val user_id: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    var members: List<NewMemberDomain> = listOf()
)
