package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class GetFamilyMembersUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String) = networkRepository.getFamilyMembers(familyId)
}