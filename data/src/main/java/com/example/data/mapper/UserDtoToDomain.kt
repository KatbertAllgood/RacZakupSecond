package com.example.data.mapper

import com.example.data.models.UserDtoData
import com.example.domain.models.UserDtoDomain

class UserDtoToDomain(
    userDtoData: UserDtoData
) {
    private val userDto = UserToDomain(userDtoData.userDto).toDomain()
    fun toDomain() = UserDtoDomain (
        userDto
    )
}