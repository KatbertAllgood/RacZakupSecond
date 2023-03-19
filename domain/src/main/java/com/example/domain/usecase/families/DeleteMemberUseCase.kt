package com.example.domain.usecase.families

import com.example.domain.repository.NetworkRepository

class DeleteMemberUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        familyId: String,
        memberId: String
    ) = networkRepository.deleteMember(
        familyId,
        memberId
    )
}