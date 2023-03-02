package com.example.data.mapper

import com.example.data.models.families.MemberData
import com.example.domain.models.families.MemberDomain

class MemberToData(
    private val memberDomain: MemberDomain
) {
    fun toData() = MemberData(
        memberDomain.id,
        memberDomain.isUser,
        memberDomain.name,
        memberDomain.height,
        memberDomain.weight,
        memberDomain.birthday,
        memberDomain.gender,
        memberDomain.age,
        memberDomain.preferences,
        memberDomain.position
    )
}