package com.example.data.mapper

import com.example.data.models.auth.CodeResponseData
import com.example.domain.models.auth.CodeResponseDomain

class CodeResponseToData(
    private val codeResponseDomain: CodeResponseDomain
) {
    private val user = UserToData(codeResponseDomain.user).toData()
    fun toData() = CodeResponseData(
        codeResponseDomain.status,
        codeResponseDomain.accessToken,
        codeResponseDomain.refreshToken,
        user
    )
}