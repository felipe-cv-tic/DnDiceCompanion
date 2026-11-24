package com.dsa.dndicecompanion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AjudaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        TemaAltContrast.aplicar(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajuda)

        findViewById<MaterialButton>(R.id.btn_comencar).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
