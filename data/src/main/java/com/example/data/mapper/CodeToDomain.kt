package com.example.data.mapper

import com.example.data.models.auth.CodeData
import com.example.domain.models.auth.CodeDomain

class CodeToDomain(
    private val codeData: CodeData
) {
    fun toDomain() = CodeDomain(
        codeData.phone,
        codeData.code
    )
}