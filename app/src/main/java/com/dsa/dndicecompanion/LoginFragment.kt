package com.dsa.dndicecompanion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = requireActivity() as MainActivity

        view.findViewById<MaterialButton>(R.id.btn_entrar).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        view.findViewById<MaterialButton>(R.id.btn_registre).setOnClickListener {
            main.obrirPantalla(RegisterFragment())
        }

        view.findViewById<View>(R.id.noLogin).setOnClickListener {
            main.mostrarPersonatges()
        }

        view.findViewById<View>(R.id.btn_preferencies).setOnClickListener {
            main.obrirPantalla(SettingsFragment())
        }
    }
}
