package com.institucion.soporte.soporteestudiantil.controller

import com.institucion.soporte.soporteestudiantil.model.Usuario
import com.institucion.soporte.soporteestudiantil.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tickets")
class TicketController(
    private val ticketService: TicketService
) {
    private val usuarios = listOf(
        Usuario(id = 1, nombre = "Juan Perez", correo = "juan@example.com"),
        Usuario(id = 2, nombre = "Ana Gomez", correo = "ana@example.com")
    )

    @PostMapping
    fun createTicket(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val usuario = usuarios[0] // Ejemplo: Selección de usuario fijo
        val ticket = ticketService.crearTicket(request["titulo"] ?: "", request["descripcion"] ?: "", usuario)
        return ResponseEntity.status(HttpStatus.CREATED).body(mapOf("status" to "success", "data" to ticket))
    }

    @GetMapping
    fun getTickets(): ResponseEntity<Any> {
        val tickets = ticketService.obtenerTickets()
        return ResponseEntity.ok(mapOf("status" to "success", "data" to tickets))
    }

    @PostMapping("/{id}/respuestas")
    fun addRespuesta(@PathVariable id: Long, @RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val usuario = usuarios[1] // Ejemplo: Selección de usuario fijo
        val respuesta = ticketService.agregarRespuesta(id, request["contenido"] ?: "", usuario)
        return if (respuesta != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(mapOf("status" to "success", "data" to respuesta))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("status" to "fail", "message" to "Ticket no encontrado"))
        }
    }

    @PatchMapping("/{id}/cerrar")
    fun cerrarTicket(@PathVariable id: Long): ResponseEntity<Any> {
        val ticket = ticketService.cerrarTicket(id)
        return if (ticket != null) {
            ResponseEntity.ok(mapOf("status" to "success", "data" to ticket))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("status" to "fail", "message" to "Ticket no encontrado"))
        }
    }
}