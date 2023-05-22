package com.example.domain.usecase.packs

import com.example.domain.repository.NetworkRepository

class AddProductToHealthySetUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        healthySetId: String
    ) = networkRepository.addProductToHealthySet(
        healthySetId
    )
}