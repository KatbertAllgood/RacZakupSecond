package com.example.data.mapper

import com.example.data.models.auth.CodeData
import com.example.domain.models.auth.CodeDomain

class CodeToData(
    private val codeDomain: CodeDomain
) {
    fun toData() = CodeData(
        codeDomain.phone,
        codeDomain.code
    )
}