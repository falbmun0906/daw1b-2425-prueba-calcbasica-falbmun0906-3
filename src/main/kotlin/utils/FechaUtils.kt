package org.example.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object FechaUtils {

    fun obtenerFechaParaNombre(): String {
        val fechaHora = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        return fechaHora.format(formatter)
    }

    fun obtenerFechaFormateada(): String {
        val fechaHora = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return fechaHora.format(formatter)
    }
}