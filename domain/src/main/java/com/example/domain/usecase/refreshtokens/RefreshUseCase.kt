package com.example.domain.usecase.refreshtokens

import com.example.domain.repository.NetworkRepository

class RefreshUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
//        accessToken: String,
//        refreshToken: String
    ) = networkRepository.refresh()
}