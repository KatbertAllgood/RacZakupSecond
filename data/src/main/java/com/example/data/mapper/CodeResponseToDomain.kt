package com.example.data.mapper

import com.example.data.models.auth.CodeResponseData
import com.example.domain.models.auth.CodeResponseDomain

class CodeResponseToDomain(
    private val codeResponseData: CodeResponseData
) {
    private val user = UserToDomain(codeResponseData.user).toDomain()
    fun toDomain() = CodeResponseDomain(
        codeResponseData.status,
        codeResponseData.accessToken,
        codeResponseData.refreshToken,
        user
    )
}