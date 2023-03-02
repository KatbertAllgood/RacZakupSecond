package com.example.data.mapper

import com.example.data.models.auth.RefreshResponseData
import com.example.domain.models.auth.RefreshResponseDomain

class RefreshResponseToDomain(
    private val refreshResponseData: RefreshResponseData
) {
    private val data = UserDtoToDomain(refreshResponseData.data).toDomain()
    fun toDomain() = RefreshResponseDomain(
        refreshResponseData.status,
        data,
        refreshResponseData.accessToken,
        refreshResponseData.refreshToken
    )
}