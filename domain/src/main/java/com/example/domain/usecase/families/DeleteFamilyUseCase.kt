package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class DeleteFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(familyId: String) = networkRepository.deleteFamily(familyId)
}