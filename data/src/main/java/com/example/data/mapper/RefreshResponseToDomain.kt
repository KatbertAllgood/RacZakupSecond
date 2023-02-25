package com.example.data.mapper

import com.example.data.models.RefreshResponseData
import com.example.domain.models.RefreshResponseDomain

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