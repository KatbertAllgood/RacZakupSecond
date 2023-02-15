package com.example.domain.usecase.sharedpreferences

import com.example.domain.repository.SharedPreferencesRepository

class UpdatePreferenceUseCase(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) {
    fun invoke(key: String, valueAny: Any) =
        sharedPreferencesRepository.updatePreference(key, valueAny)
}