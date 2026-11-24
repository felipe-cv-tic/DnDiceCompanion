package com.dsa.dndicecompanion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class DausFragment : Fragment(R.layout.fragment_daus) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvResult = view.findViewById<TextView>(R.id.tv_dice_result)
        val ivDice = view.findViewById<ImageView>(R.id.iv_dice_image)

        /** Tira un dau i mostra el resultat. */
        fun roll(sides: Int) {
            val res = Random.nextInt(1, sides + 1)
            tvResult.text = getString(R.string.dice_result, sides, res)
            ivDice?.setImageResource(
                when (sides) {
                    4 -> R.drawable.dice4
                    6 -> R.drawable.dice6
                    8 -> R.drawable.dice8
                    10 -> R.drawable.dice10
                    12 -> R.drawable.dice12
                    else -> R.drawable.dice20
                }
            )
            Log.d("DausFragment", "Tirada d$sides: $res")
        }

        view.findViewById<MaterialButton>(R.id.btn_d4)?.setOnClickListener { roll(4) }
        view.findViewById<MaterialButton>(R.id.btn_d6)?.setOnClickListener { roll(6) }
        view.findViewById<MaterialButton>(R.id.btn_d8)?.setOnClickListener { roll(8) }
        view.findViewById<MaterialButton>(R.id.btn_d10)?.setOnClickListener { roll(10) }
        view.findViewById<MaterialButton>(R.id.btn_d12)?.setOnClickListener { roll(12) }
        view.findViewById<MaterialButton>(R.id.btn_d20)?.setOnClickListener { roll(20) }
    }
}
