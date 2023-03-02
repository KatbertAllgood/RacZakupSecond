package com.example.data.mapper

import com.example.data.models.families.FamilyData
import com.example.data.models.families.MemberData
import com.example.domain.models.families.FamilyDomain
import com.example.domain.models.families.MemberDomain

class FamilyToDomain(
    private val familyData: FamilyData
) {
    private var members: MutableList<MemberDomain> = mutableListOf()

    init {
        for (i in familyData.members) {
            members.add(MemberToDomain(i).toDomain())
        }
    }

    fun toDomain() = FamilyDomain(
        familyData.id,
        familyData.name,
        members,
    )
}