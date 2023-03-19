package com.example.data.mapper

import com.example.data.models.ServerResponseData
import com.example.domain.models.ServerResponseDomain

class ServerResponseToDomain(
    private val serverResponseData: ServerResponseData
) {
    fun toDomain() = ServerResponseDomain(
        serverResponseData.success,
        serverResponseData.message
    )
}