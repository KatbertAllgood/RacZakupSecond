package com.example.data.mapper

import com.example.data.models.families.NewFamilyUpdateData
import com.example.domain.models.families.NewFamilyUpdateDomain

class NewFamilyUpdateToDomain(
    private val newFamilyUpdateData: NewFamilyUpdateData
) {
    fun toDomain() = NewFamilyUpdateDomain(
        newFamilyUpdateData.name
    )
}