package com.example.domain.usecase.sharedpreferences

import com.example.domain.repository.SharedPreferencesRepository

class GetStringPreferenceUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    fun invoke(key: String) = sharedPreferencesRepository
        .getStringPreference(key)
}