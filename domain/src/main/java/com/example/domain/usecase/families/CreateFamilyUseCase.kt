package com.example.domain.usecase.families

import com.example.domain.models.families.FamilyDomain
import com.example.domain.repository.NetworkRepository

class CreateFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(family: FamilyDomain) = networkRepository.createFamily(family)
}