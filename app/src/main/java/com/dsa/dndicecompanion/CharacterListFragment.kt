package com.dsa.dndicecompanion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CharacterListFragment : Fragment(R.layout.fragment_character_list) {

    private val charactersList = mutableListOf<CharacterItem>()

    private lateinit var adapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (charactersList.isEmpty()) {
            charactersList.addAll(
                listOf(
                    CharacterItem("1", getString(R.string.char_iniesta), getString(R.string.class_mago_evocacion)),
                    CharacterItem("2", getString(R.string.char_messi), getString(R.string.class_picaro_ladron)),
                    CharacterItem("3", getString(R.string.char_xavi), getString(R.string.class_clergue_vida)),
                    CharacterItem("4", getString(R.string.char_puyol), getString(R.string.class_guerrer_campio))
                )
            )
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_character_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CharacterAdapter(
            items = charactersList,
            onItemClick = { item ->
                Log.d("CharacterListFragment", "Seleccionat personatge: ${item.name}")
                Toast.makeText(requireContext(), getString(R.string.toast_personatge, item.name), Toast.LENGTH_SHORT).show()
                (requireActivity() as MainActivity).obrirPantalla(CharacterFragment.newInstance(item.name))
            },
            onEditClick = { item ->
                dialegModificarNom(item.name) { nouNom ->
                    val pos = charactersList.indexOf(item)
                    if (pos != -1) {
                        item.name = nouNom
                        adapter.notifyItemChanged(pos)
                        Log.d("CharacterListFragment", "Modificat personatge: ${item.name}")
                        Toast.makeText(requireContext(), getString(R.string.toast_modificat, item.name), Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onDeleteClick = { item ->
                val pos = charactersList.indexOf(item)
                if (pos != -1) {
                    charactersList.removeAt(pos)
                    adapter.notifyItemRemoved(pos)
                    Log.d("CharacterListFragment", "Eliminat personatge: ${item.name}")
                    Toast.makeText(requireContext(), getString(R.string.toast_eliminat, item.name), Toast.LENGTH_SHORT).show()
                }
            }
        )

        recyclerView.adapter = adapter

        view.findViewById<FloatingActionButton>(R.id.AddCharacter)?.setOnClickListener {
            val newChar = CharacterItem(
                "${System.currentTimeMillis()}",
                getString(R.string.nou_personatge, charactersList.size + 1),
                getString(R.string.class_guerrer)
            )
            charactersList.add(newChar)
            adapter.notifyItemInserted(charactersList.size - 1)
            Log.d("CharacterListFragment", "Afegit personatge: ${newChar.name}")
            Toast.makeText(requireContext(), getString(R.string.toast_afegit, newChar.name), Toast.LENGTH_SHORT).show()
        }
    }
}
