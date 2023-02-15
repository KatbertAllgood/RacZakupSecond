package com.example.data.models

import com.example.domain.models.PhoneResponseDomain
import com.google.gson.annotations.SerializedName

data class PhoneResponseData(
    @SerializedName("status")
    val status: Int = 0
) {
    //mapper
//    fun toDomain(): PhoneResponseDomain {
//        return PhoneResponseDomain(this.status)
//    }
}
