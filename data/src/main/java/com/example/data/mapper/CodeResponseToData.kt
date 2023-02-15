package com.example.data.mapper

import com.example.data.models.CodeResponseData
import com.example.domain.models.CodeResponseDomain

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