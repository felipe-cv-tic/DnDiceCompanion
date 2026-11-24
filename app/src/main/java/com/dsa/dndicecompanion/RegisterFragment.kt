package com.dsa.dndicecompanion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class RegisterFragment : Fragment(R.layout.register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.btn_entrar).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<MaterialButton>(R.id.btn_registre).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<View>(R.id.noLogin).setOnClickListener {
            (requireActivity() as MainActivity).mostrarPersonatges()
        }

        view.findViewById<View>(R.id.btn_preferencies).setOnClickListener {
            (requireActivity() as MainActivity).obrirPantalla(SettingsFragment())
        }
    }
}
