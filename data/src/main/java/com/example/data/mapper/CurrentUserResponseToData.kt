package com.example.data.mapper

import com.example.data.models.CurrentUserResponseData
import com.example.domain.models.CurrentUserResponseDomain

class CurrentUserResponseToData(
    val currentUserResponseDomain: CurrentUserResponseDomain
) {
    val user = UserToData(currentUserResponseDomain.user).toData()
    fun toData() = CurrentUserResponseData(
        currentUserResponseDomain.status,
        user
    )
}