package com.example.data.mapper

import com.example.data.models.auth.UserData
import com.example.domain.models.auth.UserDomain

class UserToData(
    private val userDomain: UserDomain
) {
    fun toData() = UserData(
        userDomain.authId,
        userDomain.role,
        userDomain.userId,
        userDomain.iat,
        userDomain.exp
    )
}