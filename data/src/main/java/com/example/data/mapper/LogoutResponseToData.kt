package com.example.data.mapper

import com.example.data.models.auth.LogoutResponseData
import com.example.domain.models.auth.LogoutResponseDomain

class LogoutResponseToData(
    private val logoutResponseDomain: LogoutResponseDomain
) {
    fun toData() = LogoutResponseData(
        logoutResponseDomain.status,
        logoutResponseDomain.refreshToken
    )
}