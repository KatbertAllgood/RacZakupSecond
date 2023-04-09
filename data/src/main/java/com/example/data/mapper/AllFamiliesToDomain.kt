package com.example.data.mapper

import com.example.data.models.families.AllFamiliesData
import com.example.data.models.families.NewFamilyData
import com.example.domain.models.families.AllFamiliesDomain
import com.example.domain.models.families.NewFamilyDomain

class AllFamiliesToDomain(
    private val allFamiliesData: AllFamiliesData
) {
    private val results: MutableList<NewFamilyDomain> = mutableListOf()

    init {
        for (i in allFamiliesData.results) {
            results.add(NewFamilyToDomain(i).toDomain())
        }
    }

    fun toDomain() = AllFamiliesDomain(
        results,
        allFamiliesData.total,
        allFamiliesData.limit,
        allFamiliesData.offset
    )
}