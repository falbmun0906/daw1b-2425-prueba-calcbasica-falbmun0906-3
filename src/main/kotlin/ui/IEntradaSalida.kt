package org.example.ui

interface IEntradaSalida {

    fun pedirInfo(mensaje: String): String
    fun mostrar(mensaje: String)
    fun mostrarError(error: String)
    fun pedirDouble(prompt: String): Double
    fun pedirEntero(prompt: String): Int
    fun pedirChar(mensaje: String): Char
    fun preguntarSiNo(mensaje: String) : Boolean
}