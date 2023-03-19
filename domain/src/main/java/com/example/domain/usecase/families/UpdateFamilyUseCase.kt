package com.example.domain.usecase.families

import com.example.domain.models.families.FamilyDomain
import com.example.domain.repository.NetworkRepository

class UpdateFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        updatedFamily: FamilyDomain
    ) = networkRepository.updateFamily(
        familyId,
        updatedFamily
    )
}