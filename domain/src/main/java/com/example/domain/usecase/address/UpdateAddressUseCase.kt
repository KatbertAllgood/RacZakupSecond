package com.example.domain.usecase.address

import com.example.domain.models.addresses.AddressParamsDomain
import com.example.domain.repository.NetworkRepository

class UpdateAddressUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        addressId: String,
        address: AddressParamsDomain
    ) = networkRepository.updateAddress(addressId, address)
}