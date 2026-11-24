package com.dsa.dndicecompanion

/** Dades d'una arma. */
data class ArmaItem(
    val id: String,
    var name: String,
    val stats: String,
    val type: String,
    val description: String,
    var isExpanded: Boolean = false
)
