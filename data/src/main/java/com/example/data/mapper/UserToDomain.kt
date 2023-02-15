package com.example.data.mapper

import com.example.data.models.UserData
import com.example.domain.models.UserDomain

class UserToDomain(
    private val userData: UserData
) {
    fun toDomain() = UserDomain(
        userData.authId,
        userData.role,
        userData.userId
    )
}