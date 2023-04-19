package com.example.domain.usecase.packs

import com.example.domain.models.packs.HealthySetParamsRequestDomain
import com.example.domain.repository.NetworkRepository

class CreateHealthySetParamsUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        healthySetParamsRequestDomain: HealthySetParamsRequestDomain
    ) = networkRepository.createHealthySetParams(
        healthySetParamsRequestDomain
    )
}