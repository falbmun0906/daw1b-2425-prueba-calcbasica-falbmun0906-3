package org.example.ui

import java.util.*

class Consola : IEntradaSalida {

    private val scanner = Scanner(System.`in`)

    override fun pedirInfo(mensaje: String): String {
        mostrar(mensaje)
        return scanner.nextLine()
    }

    override fun mostrar(mensaje: String) {
        println(mensaje)
    }

    override fun mostrarError(error: String) {
        System.err.println("ERROR: $error")
    }

    override fun pedirDouble(prompt: String): Double {
        while (true) {
            mostrar(prompt)
            val entrada = readlnOrNull()
            try {
                return entrada?.toDouble() ?: throw NumberFormatException()
            } catch (e: NumberFormatException) {
                mostrarError("ERROR: El número introducido no es válido.")
            }
        }
    }

    override fun pedirEntero(prompt: String): Int {
        while (true) {
            print(prompt)
            val entrada = readlnOrNull()
            try {
                return entrada?.toInt() ?: throw NumberFormatException()
            } catch (e: NumberFormatException) {
                mostrarError("ERROR: El número introducido no es un entero.")
            } catch (e: Exception) {
                mostrarError("ERROR: Entrada no válida.")
            }
        }
    }

    override fun pedirChar(mensaje: String): Char {
        return pedirInfo(mensaje).firstOrNull() ?: ' '
    }

    override fun preguntarSiNo(mensaje: String): Boolean {
        val respuesta = pedirInfo(mensaje)
        return respuesta.equals("S", ignoreCase = true)
    }

}