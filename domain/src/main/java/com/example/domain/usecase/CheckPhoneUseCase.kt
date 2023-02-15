package com.example.domain.usecase

import com.example.domain.models.PhoneDomain
import com.example.domain.repository.NetworkRepository

class CheckPhoneUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(phoneDomain: PhoneDomain) = networkRepository.checkPhone(phoneDomain)
}