package com.dsa.dndicecompanion

import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

/** Mostra un diàleg per canviar el nom d'un element. */
fun Fragment.dialegModificarNom(nomActual: String, onAcceptar: (String) -> Unit) {
    val input = EditText(requireContext())
    input.setText(nomActual)
    input.hint = getString(R.string.dialeg_modificar_hint)
    input.importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO

    AlertDialog.Builder(requireContext())
        .setTitle(R.string.dialeg_modificar_titol)
        .setView(input)
        .setPositiveButton(android.R.string.ok) { _, _ ->
            val nouNom = input.text.toString().trim()
            if (nouNom.isNotEmpty() && isAdded) onAcceptar(nouNom)
        }
        .setNegativeButton(android.R.string.cancel, null)
        .show()
}
