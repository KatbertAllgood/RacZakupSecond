package com.example.domain.models.families

data class MemberDomain(
    val id: Int = 0,
    val isUser: Boolean = false,
    val name: String = "",
    val height: Int = 0,
    val weight: Double = 0.0,
    val birthday: String = "",
    val gender: String = "",

    val age: Int = 0,
    var preferences: List<Int> = listOf(),
    var position: Int = 0
)
