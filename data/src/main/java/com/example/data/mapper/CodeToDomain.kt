package com.example.data.mapper

import com.example.data.models.CodeData
import com.example.domain.models.CodeDomain

class CodeToDomain(
    private val codeData: CodeData
) {
    fun toDomain() = CodeDomain(
        codeData.phone,
        codeData.code
    )
}