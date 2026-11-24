package com.dsa.dndicecompanion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ArmesFragment : Fragment(R.layout.fragment_armes) {

    private val armesList = mutableListOf<ArmaItem>()

    private lateinit var adapter: ArmaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (armesList.isEmpty()) {
            armesList.addAll(
                listOf(
                    ArmaItem("1", getString(R.string.arma_espasa_nom), getString(R.string.arma_espasa_stats), getString(R.string.arma_espasa_tipus), getString(R.string.arma_espasa_desc)),
                    ArmaItem("2", getString(R.string.arma_escut_nom), getString(R.string.arma_escut_stats), getString(R.string.arma_escut_tipus), getString(R.string.arma_escut_desc)),
                    ArmaItem("3", getString(R.string.arma_arc_nom), getString(R.string.arma_arc_stats), getString(R.string.arma_arc_tipus), getString(R.string.arma_arc_desc))
                )
            )
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_armes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ArmaAdapter(
            items = armesList,
            onItemClick = { item ->
                Log.d("ArmesFragment", "Seleccionada arma: ${item.name}")
            },
            onEditClick = { item ->
                dialegModificarNom(item.name) { nouNom ->
                    val pos = armesList.indexOf(item)
                    if (pos != -1) {
                        item.name = nouNom
                        adapter.notifyItemChanged(pos)
                        Log.d("ArmesFragment", "Modificada arma: ${item.name}")
                        Toast.makeText(requireContext(), getString(R.string.toast_modificada, item.name), Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onDeleteClick = { item ->
                val pos = armesList.indexOf(item)
                if (pos != -1) {
                    armesList.removeAt(pos)
                    adapter.notifyItemRemoved(pos)
                    Log.d("ArmesFragment", "Eliminada arma: ${item.name}")
                    Toast.makeText(requireContext(), getString(R.string.toast_eliminada, item.name), Toast.LENGTH_SHORT).show()
                }
            }
        )

        recyclerView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.AddArma)?.setOnClickListener {
            val newArma = ArmaItem(
                "${System.currentTimeMillis()}",
                getString(R.string.nova_arma_nom, armesList.size + 1),
                getString(R.string.nova_arma_stats),
                getString(R.string.nova_arma_tipus),
                getString(R.string.nova_arma_desc)
            )
            armesList.add(newArma)
            adapter.notifyItemInserted(armesList.size - 1)
            Log.d("ArmesFragment", "Afegida arma: ${newArma.name}")
            Toast.makeText(requireContext(), getString(R.string.toast_afegida, newArma.name), Toast.LENGTH_SHORT).show()
        }
    }
}
