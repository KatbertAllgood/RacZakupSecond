package com.example.data.mapper

import com.example.data.models.auth.UserData
import com.example.domain.models.auth.UserDomain

class UserToDomain(
    private val userData: UserData
) {
    fun toDomain() = UserDomain(
        userData.authId,
        userData.role,
        userData.userId,
        userData.iat,
        userData.exp
    )
}