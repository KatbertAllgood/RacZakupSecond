package com.example.domain.usecase.families

import com.example.domain.models.families.NewMemberUpdateDomain
import com.example.domain.repository.NetworkRepository

class UpdateMemberUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        memberId: String,
        updatedMember: NewMemberUpdateDomain
    ) = networkRepository.updateMember(
        familyId,
        memberId,
        updatedMember
    )
}