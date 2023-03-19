package com.example.data.mapper

import com.example.data.models.ServerResponseData
import com.example.domain.models.ServerResponseDomain

class ServerResponseToData(
    private val serverResponseDomain: ServerResponseDomain
) {
    fun toData() = ServerResponseData(
        serverResponseDomain.success,
        serverResponseDomain.message
    )
}