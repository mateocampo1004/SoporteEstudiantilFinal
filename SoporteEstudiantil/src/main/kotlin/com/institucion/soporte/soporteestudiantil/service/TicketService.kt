package com.institucion.soporte.soporteestudiantil.service

import com.institucion.soporte.soporteestudiantil.model.Respuesta
import com.institucion.soporte.soporteestudiantil.model.Ticket
import com.institucion.soporte.soporteestudiantil.model.Usuario
import org.springframework.stereotype.Service

@Service
class TicketService {
    private val tickets = mutableListOf<Ticket>()
    private var ticketIdCounter = 1L
    private var respuestaIdCounter = 1L

    fun crearTicket(titulo: String, descripcion: String, usuario: Usuario): Ticket {
        val ticket = Ticket(id = ticketIdCounter++, titulo = titulo, descripcion = descripcion, usuario = usuario)
        tickets.add(ticket)
        return ticket
    }

    fun obtenerTickets(): List<Ticket> = tickets

    fun agregarRespuesta(ticketId: Long, contenido: String, usuario: Usuario): Respuesta? {
        val ticket = tickets.find { it.id == ticketId } ?: return null
        val respuesta = Respuesta(id = respuestaIdCounter++, contenido = contenido, usuario = usuario)
        ticket.respuestas.add(respuesta)
        return respuesta
    }

    fun cerrarTicket(ticketId: Long): Ticket? {
        val ticket = tickets.find { it.id == ticketId } ?: return null
        ticket.estado = "Cerrado"
        return ticket
    }
}