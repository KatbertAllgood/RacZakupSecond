package com.example.data.mapper

import com.example.data.models.families.FamilyData
import com.example.data.models.families.MemberData
import com.example.domain.models.families.FamilyDomain

class FamilyToData(
    private val familyDomain: FamilyDomain
) {
    private var members: MutableList<MemberData> = mutableListOf()

    init {
        for (i in familyDomain.members) {
            members.add(MemberToData(i).toData())
        }
    }

    fun toData() = FamilyData(
        familyDomain.id,
        familyDomain.name,
        members,
    )
}