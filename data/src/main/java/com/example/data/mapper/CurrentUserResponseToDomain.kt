package com.example.data.mapper

import com.example.data.models.auth.CurrentUserResponseData
import com.example.domain.models.auth.CurrentUserResponseDomain

class CurrentUserResponseToDomain(
    val currentUserResponseData: CurrentUserResponseData
) {
    val user = UserToDomain(currentUserResponseData.user).toDomain()
    fun toDomain() = CurrentUserResponseDomain(
        currentUserResponseData.status,
        user
    )
}