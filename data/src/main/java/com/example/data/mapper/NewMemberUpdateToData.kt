package com.example.data.mapper

import com.example.data.models.families.NewMemberUpdateData
import com.example.domain.models.families.NewMemberUpdateDomain

class NewMemberUpdateToData(
    private val newMemberUpdateDomain: NewMemberUpdateDomain
) {
    fun toData() = NewMemberUpdateData(
        newMemberUpdateDomain.name,
        newMemberUpdateDomain.height,
        newMemberUpdateDomain.weight,
        newMemberUpdateDomain.birthday,
        newMemberUpdateDomain.gender,
    )
}