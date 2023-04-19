package com.example.domain.usecase.geo

import com.example.domain.models.geo.RequestCoordinatesDomain
import com.example.domain.repository.NetworkRepository

class ResolveCoordinatesUseCase(
    private val networkRepository: NetworkRepository
) {
    fun invoke(
        coordinatesDomain: RequestCoordinatesDomain
    ) = networkRepository.resolveCoordinates(coordinatesDomain)
}