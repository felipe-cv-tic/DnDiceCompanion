package com.dsa.dndicecompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Adapter de la llista de personatges. */
class CharacterAdapter(
    private val items: MutableList<CharacterItem>,
    private val onItemClick: (CharacterItem) -> Unit,
    private val onEditClick: (CharacterItem) -> Unit,
    private val onDeleteClick: (CharacterItem) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    /** Guarda les vistes d'una fila de personatge. */
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_character_name)
        val tvClass: TextView = itemView.findViewById(R.id.tv_character_class)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete_character)
    }

    /** Crea una fila nova a partir del layout. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    /** Posa les dades i els listeners a la fila. */
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = items[position]
        holder.tvName.text = item.name
        holder.tvClass.text = item.characterClass

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.itemView.setOnLongClickListener {
            onEditClick(item)
            true
        }

        holder.btnDelete.setOnClickListener {
            onDeleteClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
