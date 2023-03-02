package com.example.domain.usecase.auth

import com.example.domain.models.auth.PhoneDomain
import com.example.domain.repository.NetworkRepository

class CheckPhoneUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(phoneDomain: PhoneDomain) = networkRepository.checkPhone(phoneDomain)
}