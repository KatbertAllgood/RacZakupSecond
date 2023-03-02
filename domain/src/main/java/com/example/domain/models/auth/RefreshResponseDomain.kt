package com.example.domain.models.auth

data class RefreshResponseDomain(
    val status: String = "",
    val data: UserDtoDomain,
    val accessToken: String = "",
    val refreshToken: String = ""
)
