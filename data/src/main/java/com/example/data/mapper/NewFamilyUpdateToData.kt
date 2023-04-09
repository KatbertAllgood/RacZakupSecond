package com.example.data.mapper

import com.example.data.models.families.NewFamilyUpdateData
import com.example.domain.models.families.NewFamilyUpdateDomain

class NewFamilyUpdateToData(
    private val newFamilyUpdateDomain: NewFamilyUpdateDomain
) {
    fun toData() = NewFamilyUpdateData(
        newFamilyUpdateDomain.name
    )
}