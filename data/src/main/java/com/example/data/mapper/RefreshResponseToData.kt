package com.example.data.mapper

import com.example.data.models.auth.RefreshResponseData
import com.example.domain.models.auth.RefreshResponseDomain

class RefreshResponseToData(
    private val refreshResponseDomain: RefreshResponseDomain
) {
    private val data = UserDtoToData(refreshResponseDomain.data).toData()
    fun toData() = RefreshResponseData(
        refreshResponseDomain.status,
        data,
        refreshResponseDomain.accessToken,
        refreshResponseDomain.refreshToken
    )
}