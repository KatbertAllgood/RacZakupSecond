package com.example.domain.usecase.sharedpreferences

import com.example.domain.repository.SharedPreferencesRepository

class GetIntPreferenceUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    fun ivoke(key: String) = sharedPreferencesRepository
        .getIntPreference(key)
}