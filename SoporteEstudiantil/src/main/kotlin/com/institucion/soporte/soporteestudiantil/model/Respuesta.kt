package com.institucion.soporte.soporteestudiantil.model

data class Respuesta(
    val id: Long,
    val contenido: String,
    val usuario: Usuario
)