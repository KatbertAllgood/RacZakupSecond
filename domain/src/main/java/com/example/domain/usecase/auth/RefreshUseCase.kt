package com.example.domain.usecase.auth

import com.example.domain.repository.NetworkRepository

class RefreshUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
//        accessToken: String,
//        refreshToken: String
    ) = networkRepository.refresh()
}