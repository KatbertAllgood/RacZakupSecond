package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class GetFamiliesUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.getFamilies()
}