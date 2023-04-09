package com.example.domain.usecase.families

import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewFamilyUpdateDomain
import com.example.domain.repository.NetworkRepository

class CreateFamilyUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(family: NewFamilyUpdateDomain) = networkRepository.createFamily(family)
}