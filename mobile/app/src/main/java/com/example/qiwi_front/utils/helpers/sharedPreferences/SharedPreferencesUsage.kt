package com.example.qiwi_front.utils.helpers.sharedPreferences

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import com.example.qiwi_front.utils.consts.AppSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesUsage @Inject constructor() {

    fun getStringSharedPreferences(@ApplicationContext context: Context, preference: String): String? {
        return (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        ).getString(preference, null)
    }

    fun putStringSharedPreferences(@ApplicationContext context: Context, key: String, preference: String) {
        (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .edit()
            .putString(key, preference)
            .apply()
    }

    fun removePreferenceValueSharedPreferences(@ApplicationContext context: Context, preference: String) {
        (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .edit()
            .remove(preference)
            .apply()
    }

    fun getIntSharedPreferences(@ApplicationContext context: Context, preference: String): Int {
        return (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .getInt(preference, -1)
    }

    fun putIntSharedPreferences(@ApplicationContext context: Context, preference: String, value: Int) {
        (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .edit()
            .putInt(preference, value)
            .apply()
    }

    fun clearSharedPreferences(@ApplicationContext context: Context){
        (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .edit()
            .clear()
            .apply()
    }

    fun putBoolean(@ApplicationContext context: Context, preference: String, value: Boolean){
        (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .edit()
            .putBoolean(preference, value)
            .apply()
    }

    fun getBoolean(@ApplicationContext context: Context, preference: String): Boolean{
        return (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        )
            .getBoolean(preference, false)
    }

    fun hasKey(@ApplicationContext context: Context, preference: String): Boolean{
        return (context as ContextWrapper).getSharedPreferences(
            AppSettings.SharedPreferencesEnvKey,
            AppCompatActivity.MODE_PRIVATE
        ).contains(preference)
    }

}