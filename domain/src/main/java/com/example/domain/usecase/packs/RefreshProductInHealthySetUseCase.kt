package com.example.domain.usecase.packs

import com.example.domain.repository.NetworkRepository

class RefreshProductInHealthySetUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        healthySetId: String,
        productId: String,
    ) = networkRepository.refreshProductInHealthySet(
        healthySetId,
        productId
    )
}