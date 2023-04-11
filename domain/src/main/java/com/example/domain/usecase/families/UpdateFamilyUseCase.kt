package com.example.domain.usecase.families

import com.example.domain.models.families.NewFamilyUpdateDomain
import com.example.domain.repository.NetworkRepository

class UpdateFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        updatedFamily: NewFamilyUpdateDomain
    ) = networkRepository.updateFamily(
        familyId,
        updatedFamily
    )
}