package com.dsa.dndicecompanion

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.color.MaterialColors

/** Pantalla principal amb toolbar, fragments i barra inferior. */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.barra_navegacio)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))

        val botoCharacter = findViewById<MaterialButton>(R.id.botoBarraActivitiesCharacter)
        val botoDice = findViewById<MaterialButton>(R.id.botoBarraActivitiesDice)

        if (savedInstanceState == null) {
            replaceFragment(CharacterListFragment())
        }

        marcarBotoSeleccionat(
            when (supportFragmentManager.findFragmentById(R.id.contenedorFragments)) {
                is DausFragment -> botoDice
                else -> botoCharacter
            }
        )

        botoCharacter.setOnClickListener {
            replaceFragment(CharacterListFragment())
            marcarBotoSeleccionat(botoCharacter)
        }

        botoDice.setOnClickListener {
            replaceFragment(DausFragment())
            marcarBotoSeleccionat(botoDice)
        }
    }

    /** Mostra una secció de la barra inferior. */
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.contenedorFragments, fragment)
            .commit()
    }

    /** Obre una pantalla secundària i permet tornar enrere. */
    fun obrirPantalla(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.contenedorFragments, fragment)
            .addToBackStack(null)
            .commit()
    }

    /** Marca el botó seleccionat de la barra inferior. */
    private fun marcarBotoSeleccionat(seleccionat: MaterialButton) {
        val botons = listOf(
            findViewById<MaterialButton>(R.id.botoBarraActivitiesCharacter),
            findViewById<MaterialButton>(R.id.botoBarraActivitiesDice),
            findViewById<MaterialButton>(R.id.botoBarraActivitiesSpellbook),
            findViewById<MaterialButton>(R.id.botoBarraActivitiesWeapon)
        )

        val colorNormal = ColorStateList.valueOf(
            MaterialColors.getColor(seleccionat, androidx.appcompat.R.attr.colorPrimary)
        )
        val colorSeleccionat = ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.nav_seleccionat)
        )

        botons.forEach { boto ->
            boto.backgroundTintList = if (boto == seleccionat) colorSeleccionat else colorNormal
        }
    }
}