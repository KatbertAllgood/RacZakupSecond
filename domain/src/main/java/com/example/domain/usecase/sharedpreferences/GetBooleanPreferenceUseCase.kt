package com.example.domain.usecase.sharedpreferences

import com.example.domain.repository.SharedPreferencesRepository

class GetBooleanPreferenceUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    fun invoke(key: String) = sharedPreferencesRepository
        .getBooleanPreference(key)
}