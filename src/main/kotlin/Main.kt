package org.example

import org.example.app.App
import org.example.ui.Consola
import java.io.File

fun main(args: Array<String>) {

    print("PRUEBA DE EJECUCIÓN")
    val consola = Consola()
    when (args.size) {
        0 -> {
            val file = File("./log")
            consola.mostrar("Ruta ./log creada")
        }
        1 -> {
            return
            /*val ruta = args[1]
            val file = File("./$ruta")
            consola.mostrar("Ruta ./$ruta creada")*/
        }
        4 -> {
            return
            /*val file = File("./log/${args[1]}")*/
        }
        else -> return
    }

    App(consola).iniciar()

    // list files
    // last modified

}