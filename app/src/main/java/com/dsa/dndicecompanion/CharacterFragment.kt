package com.dsa.dndicecompanion

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment

/** Fitxa del personatge seleccionat. */
class CharacterFragment : Fragment(R.layout.fragment_character) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            view.findViewById<EditText>(R.id.NameCharacterExemple)
                .setText(arguments?.getString(ARG_NOM))
        }
    }

    companion object {
        private const val ARG_NOM = "nom"

        /** Crea la fitxa amb el nom del personatge. */
        fun newInstance(nom: String): CharacterFragment {
            val fragment = CharacterFragment()
            fragment.arguments = Bundle().apply { putString(ARG_NOM, nom) }
            return fragment
        }
    }
}
