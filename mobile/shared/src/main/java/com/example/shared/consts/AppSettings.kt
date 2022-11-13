package com.example.shared.consts

open class AppSettings {

    companion object {
        @JvmStatic
        val SharedPreferencesEnvKey : String = Companion::SharedPreferencesEnvKey.name
        val IsAuth : String = Companion::IsAuth.name
        val UserRole : String = Companion::UserRole.name
        val Token : String = Companion::Token.name
    }

}