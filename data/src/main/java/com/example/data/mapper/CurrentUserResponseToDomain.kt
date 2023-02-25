package com.example.data.mapper

import com.example.data.models.CurrentUserResponseData
import com.example.domain.models.CurrentUserResponseDomain

class CurrentUserResponseToDomain(
    val currentUserResponseData: CurrentUserResponseData
) {
    val user = UserToDomain(currentUserResponseData.user).toDomain()
    fun toDomain() = CurrentUserResponseDomain(
        currentUserResponseData.status,
        user
    )
}