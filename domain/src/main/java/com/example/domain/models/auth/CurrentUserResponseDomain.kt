package com.example.domain.models.auth

data class CurrentUserResponseDomain(
    val status: Int = 0,
    val user: UserDomain
)
