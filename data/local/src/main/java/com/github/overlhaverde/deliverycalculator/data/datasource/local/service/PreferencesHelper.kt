package com.github.overlhaverde.deliverycalculator.data.datasource.local.service

import android.content.Context
import androidx.core.content.edit

class PreferencesHelper(
    context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_APP_NAME, Context.MODE_PRIVATE)

    fun saveString(key: String, data: String) = sharedPreferences.edit().run {
        putString(key, data)
        apply()
    }

    fun saveFloat(key: String, value: Float) = sharedPreferences.edit {
        putFloat(key, value)
        apply()
    }

    fun saveDouble(key: String, value: Double) = sharedPreferences.edit {
        putFloat(key, value.toFloat())
        apply()
    }

    fun saveInt(key: String, value: Int) = sharedPreferences.edit {
        putInt(key, value)
        apply()
    }

    fun saveBoolean(key: String, value: Boolean) = sharedPreferences.edit {
        putBoolean(key, value)
        apply()
    }

    fun getString(key: String) = sharedPreferences.getString(key, null)

    fun getFloat(key: String) = sharedPreferences.getFloat(key, 0f)

    fun getDouble(key: String) = sharedPreferences.getFloat(key, 0f).toDouble()

    fun getInt(key: String) = sharedPreferences.getInt(key, 0)

    fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

    fun delete(key: String) = sharedPreferences.edit {
        remove(key)
        apply()
    }

    companion object {
        private const val SHARED_PREFERENCES_APP_NAME =
            "com.github.overlhaverde.deliverycalculator"
    }
}