package com.example.domain.models.auth

data class LogoutResponseDomain(
    val status: String = "",
    val refreshToken: String = ""
)
