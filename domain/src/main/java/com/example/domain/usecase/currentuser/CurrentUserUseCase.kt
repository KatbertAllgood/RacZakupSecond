package com.example.domain.usecase.currentuser

import com.example.domain.repository.NetworkRepository

class CurrentUserUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke() = networkRepository.currentUser()
}