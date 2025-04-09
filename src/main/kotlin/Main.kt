package org.example


import org.example.app.App
import org.example.ui.Consola
import org.example.utils.Ficheros

fun main(args: Array<String>) {
    val consola = Consola()
    val ficheros = Ficheros(consola)
    val app = App(consola, ficheros)

    app.ejecutarConArgumentos(args)

    consola.mostrar("Pulsa ENTER para continuar...")
    readLine()

    app.iniciarCalculadora()
}