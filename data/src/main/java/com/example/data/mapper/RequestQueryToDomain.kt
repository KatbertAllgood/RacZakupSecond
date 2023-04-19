package com.example.data.mapper

import com.example.data.models.geo.RequestQueryData
import com.example.domain.models.geo.RequestQueryDomain

class RequestQueryToDomain(
    private val requestQueryData: RequestQueryData
) {
    fun toDomain() = RequestQueryDomain(
        requestQueryData.addressQuery
    )
}
