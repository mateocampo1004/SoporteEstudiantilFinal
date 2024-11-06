package com.institucion.soporte.soporteestudiantil.model

data class Ticket(
    val id: Long,
    val titulo: String,
    val descripcion: String,
    var estado: String = "Abierto",
    val usuario: Usuario,
    val respuestas: MutableList<Respuesta> = mutableListOf()
)