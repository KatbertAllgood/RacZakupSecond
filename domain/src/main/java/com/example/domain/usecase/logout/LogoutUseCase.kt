package com.example.domain.usecase.logout

import com.example.domain.repository.NetworkRepository

class LogoutUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.logout()
}