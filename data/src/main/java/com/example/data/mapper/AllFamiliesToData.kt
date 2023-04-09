package com.example.data.mapper

import com.example.data.models.families.AllFamiliesData
import com.example.data.models.families.NewFamilyData
import com.example.domain.models.families.AllFamiliesDomain

class AllFamiliesToData(
    private val allFamiliesDomain: AllFamiliesDomain
) {
    private val results: MutableList<NewFamilyData> = mutableListOf()

    init {
        for (i in allFamiliesDomain.results) {
            results.add(NewFamilyToData(i).toData())
        }
    }

    fun toData() = AllFamiliesData(
        results,
        allFamiliesDomain.total,
        allFamiliesDomain.limit,
        allFamiliesDomain.offset
    )
}