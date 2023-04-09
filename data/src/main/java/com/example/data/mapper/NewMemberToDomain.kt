package com.example.data.mapper

import com.example.data.models.families.NewMemberData
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewMemberDomain

class NewMemberToDomain(
    private val newMemberData: NewMemberData
) {
    private val family: NewFamilyDomain = NewFamilyToDomain(newMemberData.family).toDomain()

    fun toDomain() = NewMemberDomain(
        newMemberData.id,
        newMemberData.name,
        newMemberData.height,
        newMemberData.weight,
        newMemberData.birthday,
        newMemberData.gender,
        newMemberData.createdAt,
        newMemberData.updatedAt,
        family
    )
}