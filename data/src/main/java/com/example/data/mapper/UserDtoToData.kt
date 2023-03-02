package com.example.data.mapper

import com.example.data.models.auth.UserDtoData
import com.example.domain.models.auth.UserDtoDomain

class UserDtoToData(
    userDtoDomain: UserDtoDomain
) {
    private val userDto = UserToData(userDtoDomain.userDto).toData()
    fun toData() = UserDtoData (
        userDto
            )
}