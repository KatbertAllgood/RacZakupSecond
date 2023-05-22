package com.example.domain.usecase.packs

import com.example.domain.models.packs.HealthySetParamsAmountOfProductRequestDomain
import com.example.domain.repository.NetworkRepository

class ChangeAmountOfProductInHealthySetUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        healthySetId: String,
        productId: String,
        amount: HealthySetParamsAmountOfProductRequestDomain
    ) = networkRepository.changeAmountOfProductInHealthySet(
        healthySetId,
        productId,
        amount
    )
}