package com.example.data.mapper

import com.example.data.models.LogoutResponseData
import com.example.domain.models.LogoutResponseDomain

class LogoutResponseToDomain(
    private val logoutResponseData: LogoutResponseData
) {
    fun toDomain() = LogoutResponseDomain(
        logoutResponseData.status,
        logoutResponseData.refreshToken
    )
}