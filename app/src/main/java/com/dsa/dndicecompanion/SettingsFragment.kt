package com.dsa.dndicecompanion

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch

class SettingsFragment : Fragment(R.layout.settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialSwitch>(R.id.switch_mode_fosc).apply {
            isChecked = ModeFosc.actiu(requireContext())
            setOnCheckedChangeListener { _, actiu ->
                ModeFosc.canviar(requireContext(), actiu)
            }
        }

        view.findViewById<MaterialButton>(R.id.btn_talkback).setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        view.findViewById<MaterialButton>(R.id.btn_contrast).setOnClickListener {
            TemaAltContrast.canviar(requireActivity())
        }

        view.findViewById<View>(R.id.btn_login_retorn).setOnClickListener {
            (requireActivity() as MainActivity).obrirPantalla(LoginFragment())
        }
    }
}
