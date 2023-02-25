package com.example.data.mapper

import com.example.data.models.UserData
import com.example.domain.models.UserDomain

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