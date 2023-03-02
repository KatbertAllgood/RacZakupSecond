package com.example.data.mapper

import com.example.data.models.families.MemberData
import com.example.domain.models.families.MemberDomain

class MemberToDomain(
    private val memberData: MemberData
) {
    fun toDomain() = MemberDomain(
        memberData.id,
        memberData.isUser,
        memberData.name,
        memberData.height,
        memberData.weight,
        memberData.birthday,
        memberData.gender,
        memberData.age,
        memberData.preferences,
        memberData.position
    )
}