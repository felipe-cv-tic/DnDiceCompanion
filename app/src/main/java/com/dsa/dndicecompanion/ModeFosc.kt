package com.dsa.dndicecompanion

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

object ModeFosc {

    private const val PREFS = "preferencies"
    private const val CLAU = "mode_fosc"

    fun aplicar(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val mode = when {
            !prefs.contains(CLAU) -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            prefs.getBoolean(CLAU, false) -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_NO
        }
        if (AppCompatDelegate.getDefaultNightMode() != mode) {
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }

    fun actiu(context: Context): Boolean =
        (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

    fun canviar(context: Context, actiu: Boolean) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit {
            putBoolean(CLAU, actiu)
        }
        aplicar(context)
    }
}
