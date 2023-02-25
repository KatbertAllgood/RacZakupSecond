package com.example.data.mapper

import com.example.data.models.LogoutResponseData
import com.example.domain.models.LogoutResponseDomain

class LogoutResponseToData(
    private val logoutResponseDomain: LogoutResponseDomain
) {
    fun toData() = LogoutResponseData(
        logoutResponseDomain.status,
        logoutResponseDomain.refreshToken
    )
}