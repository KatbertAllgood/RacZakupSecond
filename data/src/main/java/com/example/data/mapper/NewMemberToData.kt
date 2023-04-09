package com.example.data.mapper

import com.example.data.models.families.NewFamilyData
import com.example.data.models.families.NewMemberData
import com.example.domain.models.families.NewMemberDomain

class NewMemberToData(
    private val newMemberDomain: NewMemberDomain
) {
    private val family: NewFamilyData = NewFamilyToData(newMemberDomain.family).toData()

    fun toData() = NewMemberData(
        newMemberDomain.id,
        newMemberDomain.name,
        newMemberDomain.height,
        newMemberDomain.weight,
        newMemberDomain.birthday,
        newMemberDomain.gender,
        newMemberDomain.createdAt,
        newMemberDomain.updatedAt,
        family
    )
}