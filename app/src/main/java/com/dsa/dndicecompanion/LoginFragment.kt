package com.dsa.dndicecompanion

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val main = requireActivity() as MainActivity

        val tilUsuari = view.findViewById<TextInputLayout>(R.id.til_usuari)
        val tilContrasenya = view.findViewById<TextInputLayout>(R.id.til_contrasenya)
        val etUsuari = view.findViewById<TextInputEditText>(R.id.et_usuari)
        val etContrasenya = view.findViewById<TextInputEditText>(R.id.et_contrasenya)

        viewModel.errors.observe(viewLifecycleOwner) { errors ->
            tilUsuari.error = errors.usuari?.let { getString(it) }
            tilContrasenya.error = errors.contrasenya?.let { getString(it) }
        }

        viewModel.loginCorrecte.observe(viewLifecycleOwner) { correcte ->
            if (correcte) {
                Toast.makeText(requireContext(), getString(R.string.toast_benvingut, etUsuari.text.toString().trim()), Toast.LENGTH_SHORT).show()
                main.mostrarPersonatges()
            }
        }

        view.findViewById<MaterialButton>(R.id.btn_entrar).setOnClickListener {
            viewModel.iniciarSessio(
                etUsuari.text.toString(),
                etContrasenya.text.toString()
            )
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