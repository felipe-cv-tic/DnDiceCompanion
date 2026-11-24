package com.dsa.dndicecompanion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MagiesFragment : Fragment(R.layout.fragment_magies) {

    private val magiesList = mutableListOf<MagiaItem>()

    private lateinit var adapter: MagiaAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (magiesList.isEmpty()) {
            magiesList.addAll(
                listOf(
                    MagiaItem("1", getString(R.string.magia_bola_nom), getString(R.string.magia_bola_stats), getString(R.string.magia_bola_nivell), getString(R.string.magia_bola_desc)),
                    MagiaItem("2", getString(R.string.magia_gel_nom), getString(R.string.magia_gel_stats), getString(R.string.magia_gel_nivell), getString(R.string.magia_gel_desc)),
                    MagiaItem("3", getString(R.string.magia_curacio_nom), getString(R.string.magia_curacio_stats), getString(R.string.magia_curacio_nivell), getString(R.string.magia_curacio_desc))
                )
            )
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_magies)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MagiaAdapter(
            items = magiesList,
            onItemClick = { item ->
                Log.d("MagiesFragment", "Seleccionada màgia: ${item.name}")
            },
            onEditClick = { item ->
                dialegModificarNom(item.name) { nouNom ->
                    val pos = magiesList.indexOf(item)
                    if (pos != -1) {
                        item.name = nouNom
                        adapter.notifyItemChanged(pos)
                        Log.d("MagiesFragment", "Modificada màgia: ${item.name}")
                        Toast.makeText(requireContext(), getString(R.string.toast_modificada, item.name), Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onDeleteClick = { item ->
                val pos = magiesList.indexOf(item)
                if (pos != -1) {
                    magiesList.removeAt(pos)
                    adapter.notifyItemRemoved(pos)
                    Log.d("MagiesFragment", "Eliminada màgia: ${item.name}")
                    Toast.makeText(requireContext(), getString(R.string.toast_eliminada, item.name), Toast.LENGTH_SHORT).show()
                }
            }
        )

        recyclerView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.AddMagic)?.setOnClickListener {
            val newMagic = MagiaItem(
                "${System.currentTimeMillis()}",
                getString(R.string.nova_magia_nom, magiesList.size + 1),
                getString(R.string.nova_magia_stats),
                getString(R.string.nova_magia_nivell),
                getString(R.string.nova_magia_desc)
            )
            magiesList.add(newMagic)
            adapter.notifyItemInserted(magiesList.size - 1)
            Log.d("MagiesFragment", "Afegida màgia: ${newMagic.name}")
            Toast.makeText(requireContext(), getString(R.string.toast_afegida, newMagic.name), Toast.LENGTH_SHORT).show()
        }
    }
}
