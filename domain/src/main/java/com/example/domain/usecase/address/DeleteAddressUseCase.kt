package com.example.domain.usecase.address

import com.example.domain.repository.NetworkRepository

class DeleteAddressUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        addressId: String
    ) = networkRepository.deleteAddress(addressId)
}