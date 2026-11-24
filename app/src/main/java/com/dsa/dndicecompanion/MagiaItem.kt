package com.dsa.dndicecompanion

/** Dades d'una màgia. */
data class MagiaItem(
    val id: String,
    var name: String,
    val stats: String,
    val level: String,
    val description: String,
    var isExpanded: Boolean = false
)
