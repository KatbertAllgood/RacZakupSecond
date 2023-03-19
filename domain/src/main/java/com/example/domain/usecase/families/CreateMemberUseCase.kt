package com.example.domain.usecase.families

import com.example.domain.models.families.MemberDomain
import com.example.domain.repository.NetworkRepository

class CreateMemberUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        newFamilyMember: MemberDomain
    ) = networkRepository.createMember(
        familyId,
        newFamilyMember
    )
}