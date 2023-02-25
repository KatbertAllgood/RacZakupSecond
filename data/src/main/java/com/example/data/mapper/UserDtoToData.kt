package com.example.data.mapper

import com.example.data.models.UserDtoData
import com.example.domain.models.UserDtoDomain

class UserDtoToData(
    userDtoDomain: UserDtoDomain
) {
    private val userDto = UserToData(userDtoDomain.userDto).toData()
    fun toData() = UserDtoData (
        userDto
            )
}