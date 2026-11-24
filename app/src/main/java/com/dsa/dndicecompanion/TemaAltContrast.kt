package com.dsa.dndicecompanion

import android.app.Activity
import android.content.Context
import androidx.core.content.edit

/** Guarda i aplica el tema d'alt contrast. */
object TemaAltContrast {

    private const val PREFS = "preferencies"
    private const val CLAU = "alt_contrast"

    /** Aplica l'alt contrast si està actiu. Cal cridar-la abans de super.onCreate. */
    fun aplicar(activity: Activity) {
        if (estaActiu(activity)) {
            activity.setTheme(R.style.Theme_DnDiceCompanion_AltContrast)
        }
    }

    /** Activa o desactiva l'alt contrast i recrea l'activity. */
    fun canviar(activity: Activity) {
        val nouValor = !estaActiu(activity)
        activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit {
            putBoolean(CLAU, nouValor)
        }
        activity.recreate()
    }

    private fun estaActiu(context: Context): Boolean =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(CLAU, false)
}
