package com.example.data.mapper

import com.example.data.models.auth.UserDtoData
import com.example.domain.models.auth.UserDtoDomain

class UserDtoToDomain(
    userDtoData: UserDtoData
) {
    private val userDto = UserToDomain(userDtoData.userDto).toDomain()
    fun toDomain() = UserDtoDomain (
        userDto
    )
}