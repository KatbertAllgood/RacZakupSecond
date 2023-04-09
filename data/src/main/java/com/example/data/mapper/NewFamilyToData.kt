package com.example.data.mapper

import com.example.data.models.families.NewFamilyData
import com.example.data.models.families.NewMemberData
import com.example.domain.models.families.NewFamilyDomain

class NewFamilyToData(
    private val newFamilyDomain: NewFamilyDomain
) {
    private val members: MutableList<NewMemberData> = mutableListOf()

    init {
        for (i in newFamilyDomain.members) {
            members.add(NewMemberToData(i).toData())
        }
    }

    fun toData() = NewFamilyData(
        newFamilyDomain.id,
        newFamilyDomain.name,
        newFamilyDomain.user_id,
        newFamilyDomain.createdAt,
        newFamilyDomain.updatedAt,
        members
    )
}