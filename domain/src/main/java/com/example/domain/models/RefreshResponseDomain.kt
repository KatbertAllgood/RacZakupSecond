package com.example.domain.models

data class RefreshResponseDomain(
    val status: String = "",
    val data: UserDtoDomain,
    val accessToken: String = "",
    val refreshToken: String = ""
)
