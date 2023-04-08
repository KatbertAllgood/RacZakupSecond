package com.example.domain.models.families

data class AllFamiliesDomain(
    val results: List<NewFamilyDomain> = listOf(),
    val total: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0
)
