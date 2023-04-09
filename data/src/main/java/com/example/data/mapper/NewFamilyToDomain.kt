package com.example.data.mapper

import com.example.data.models.families.NewFamilyData
import com.example.data.models.families.NewMemberData
import com.example.domain.models.families.NewFamilyDomain
import com.example.domain.models.families.NewMemberDomain

class NewFamilyToDomain(
    private val newFamilyData: NewFamilyData
) {
    private val members: MutableList<NewMemberDomain> = mutableListOf()

    init {
        for (i in newFamilyData.members) {
            members.add(NewMemberToDomain(i).toDomain())
        }
    }

    fun toDomain() = NewFamilyDomain(
        newFamilyData.id,
        newFamilyData.name,
        newFamilyData.user_id,
        newFamilyData.createdAt,
        newFamilyData.updatedAt,
        members
    )
}