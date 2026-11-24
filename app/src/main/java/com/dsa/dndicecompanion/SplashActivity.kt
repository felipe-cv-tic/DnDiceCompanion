package com.dsa.dndicecompanion

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val obrirAjuda = Runnable {
        startActivity(Intent(this, AjudaActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TemaAltContrast.aplicar(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imgLogo = findViewById<ImageView>(R.id.img_splash_logo)
        val tvTitle = findViewById<TextView>(R.id.tv_splash_title)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        imgLogo.startAnimation(animation)
        tvTitle.startAnimation(animation)

        handler.postDelayed(obrirAjuda, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacks(obrirAjuda)
        super.onDestroy()
    }
}
