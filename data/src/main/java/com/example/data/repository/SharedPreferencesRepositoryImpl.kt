package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(
    private val context: Context
) : SharedPreferencesRepository {

    companion object {
        const val BASE_TABLE = "base_table"
    }

    private val prefs: SharedPreferences
        get() = context
            .getSharedPreferences(BASE_TABLE, Context.MODE_PRIVATE)

    private val prefsEditor = prefs.edit()

    override fun getStringPreference(key: String): String {
        return prefs.getString(key, "").toString()
    }

    override fun getIntPreference(key: String): Int {
        return prefs.getInt(key, 0)
    }

    override fun getBooleanPreference(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    override fun updatePreference(key: String, valueAny: Any) {
        when (valueAny) {
            is String -> {
                prefsEditor.putString(key, valueAny).apply()
            }
            is Int -> {
                prefsEditor.putInt(key, valueAny).apply()
            }
            is Boolean -> {
                prefsEditor.putBoolean(key, valueAny).apply()
            }
        }
    }

}