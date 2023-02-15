package com.example.data.mapper

import com.example.data.models.CodeData
import com.example.domain.models.CodeDomain

class CodeToData(
    private val codeDomain: CodeDomain
) {
    fun toData() = CodeData(
        codeDomain.phone,
        codeDomain.code
    )
}