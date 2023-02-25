package com.example.domain.models

data class CurrentUserResponseDomain(
    val status: Int = 0,
    val user: UserDomain
)
