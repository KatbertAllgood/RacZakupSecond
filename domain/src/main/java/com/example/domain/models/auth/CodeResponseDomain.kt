package com.example.domain.models.auth

data class CodeResponseDomain(
    val status: Int = 0,
    val accessToken: String = "",
    val refreshToken: String = "",
    val user: UserDomain
)
