package com.example.domain.models

data class CodeResponseDomain(
    val status: Int = 0,
    val accessToken: String = "",
    val refreshToken: String = "",
    val user: UserDomain
)
