package com.example.domain.repository

interface SharedPreferencesRepository {

    fun updatePreference(key: String, valueAny: Any)

    fun getStringPreference(key: String): String

    fun getIntPreference(key: String): Int

    fun getBooleanPreference(key: String): Boolean

}