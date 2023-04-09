package com.example.data.mapper

import com.example.data.models.families.NewMemberUpdateData
import com.example.domain.models.families.NewMemberUpdateDomain

class NewMemberUpdateToDomain(
    private val newMemberUpdateData: NewMemberUpdateData
) {
    fun toDomain() = NewMemberUpdateDomain(
        newMemberUpdateData.name,
        newMemberUpdateData.height,
        newMemberUpdateData.weight,
        newMemberUpdateData.birthday,
        newMemberUpdateData.gender,
    )
}