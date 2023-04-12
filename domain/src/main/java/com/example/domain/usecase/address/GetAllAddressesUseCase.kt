package com.example.domain.usecase.address

import com.example.domain.repository.NetworkRepository

class GetAllAddressesUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.getAllAddresses()
}