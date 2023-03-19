package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class GetFamilyMemberUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        memberId: String) = networkRepository.getFamilyMember(familyId, memberId)
}