package com.example.domain.models.auth

data class UserDomain(
    val authId: String = "",
    val role: String = "",
    val userId: String = "",
    val iat: Int = 0,
    val exp: Int = 0
)
