package com.dsa.dndicecompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Adapter de la llista de màgies. */
class MagiaAdapter(
    private val items: MutableList<MagiaItem>,
    private val onItemClick: (MagiaItem) -> Unit,
    private val onEditClick: (MagiaItem) -> Unit,
    private val onDeleteClick: (MagiaItem) -> Unit
) : RecyclerView.Adapter<MagiaAdapter.MagiaViewHolder>() {

    /** Guarda les vistes d'una fila de màgia. */
    class MagiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNom: TextView = itemView.findViewById(R.id.tv_nom_magia)
        val tvStats: TextView = itemView.findViewById(R.id.tv_stats_magia)
        val tvNivell: TextView = itemView.findViewById(R.id.tv_nivell_magia)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_desc_magia)
        val layoutDetails: LinearLayout = itemView.findViewById(R.id.layout_details_magia)
        val btnExpand: ImageView = itemView.findViewById(R.id.btn_expand_magia)
        val btnDelete: ImageView = itemView.findViewById(R.id.btn_delete_magia)
    }

    /** Crea una fila nova a partir del layout. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_magia, parent, false)
        return MagiaViewHolder(view)
    }

    /** Posa les dades i els listeners a la fila. */
    override fun onBindViewHolder(holder: MagiaViewHolder, position: Int) {
        val item = items[position]
        holder.tvNom.text = item.name
        holder.tvStats.text = item.stats
        holder.tvNivell.text = item.level
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
