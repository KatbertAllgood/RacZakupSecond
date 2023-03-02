package com.example.data.mapper

import com.example.data.models.auth.LogoutResponseData
import com.example.domain.models.auth.LogoutResponseDomain

class LogoutResponseToDomain(
    private val logoutResponseData: LogoutResponseData
) {
    fun toDomain() = LogoutResponseDomain(
        logoutResponseData.status,
        logoutResponseData.refreshToken
    )
}