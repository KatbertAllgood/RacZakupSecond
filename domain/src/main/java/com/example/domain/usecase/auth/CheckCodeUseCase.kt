package com.example.domain.usecase.auth

import com.example.domain.models.CodeDomain
import com.example.domain.repository.NetworkRepository

class CheckCodeUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(codeDomain: CodeDomain) = networkRepository.checkCode(codeDomain)
}