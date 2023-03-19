package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class GetFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(id: String) = networkRepository.getFamily(id)
}