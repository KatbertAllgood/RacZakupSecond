package com.example.domain.usecase.geo

import com.example.domain.models.geo.RequestQueryDomain
import com.example.domain.repository.NetworkRepository

class ResolveQueryUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        query: RequestQueryDomain
    ) = networkRepository.resolveQuery(query)
}