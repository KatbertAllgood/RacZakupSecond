package com.example.domain.usecase.address

import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.repository.NetworkRepository

class CreateAddressUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        address: AddressParamsDomain
    ) = networkRepository.createAddress(address)
}