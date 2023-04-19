package com.example.data.mapper

import com.example.data.models.geo.RequestQueryData
import com.example.domain.models.geo.RequestQueryDomain

class RequestQueryToData(
    private val requestQueryDomain: RequestQueryDomain
) {
    fun toData() = RequestQueryData(
        requestQueryDomain.addressQuery
    )
}