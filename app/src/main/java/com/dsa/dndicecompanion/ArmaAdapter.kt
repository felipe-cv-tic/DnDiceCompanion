package com.dsa.dndicecompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Adapter de la llista d'armes. */
class ArmaAdapter(
    private val items: MutableList<ArmaItem>,
    private val onItemClick: (ArmaItem) -> Unit,
    private val onEditClick: (ArmaItem) -> Unit,
    private val onDeleteClick: (ArmaItem) -> Unit
) : RecyclerView.Adapter<ArmaAdapter.ArmaViewHolder>() {

    /** Guarda les vistes d'una fila d'arma. */
    class ArmaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNom: TextView = itemView.findViewById(R.id.tv_nom_arma)
        val tvStats: TextView = itemView.findViewById(R.id.tv_stats_arma)
        val tvTipus: TextView = itemView.findViewById(R.id.tv_tipus_arma)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_desc_arma)
        val layoutDetails: LinearLayout = itemView.findViewById(R.id.layout_details_arma)
        val btnExpand: ImageView = itemView.findViewById(R.id.btn_expand_arma)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete_arma)
    }

    /** Crea una fila nova a partir del layout. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_arma, parent, false)
        return ArmaViewHolder(view)
    }

    /** Posa les dades i els listeners a la fila. */
    override fun onBindViewHolder(holder: ArmaViewHolder, position: Int) {
        val item = items[position]
        holder.tvNom.text = item.name
        holder.tvStats.text = item.stats
        holder.tvTipus.text = item.type
        holder.tvDesc.text = item.description

        holder.layoutDetails.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
        holder.btnExpand.setImageResource(
            if (item.isExpanded) android.R.drawable.arrow_up_float else android.R.drawable.arrow_down_float
        )


        holder.itemView.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos == RecyclerView.NO_POSITION) return@setOnClickListener
            item.isExpanded = !item.isExpanded
            notifyItemChanged(pos)
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
