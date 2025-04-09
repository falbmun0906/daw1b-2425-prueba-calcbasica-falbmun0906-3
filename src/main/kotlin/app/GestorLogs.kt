package org.example.app

import org.example.ui.IEntradaSalida
import org.example.utils.FechaUtils
import org.example.utils.IUtilFicheros
import java.io.File

class GestorLogs(private val consola: IEntradaSalida, private val ficheros: IUtilFicheros) {

    private fun asegurarDirectorio(ruta: String): Boolean {
        val dir = File(ruta)
        if (!ficheros.existeDirectorio(ruta)) {
            val creado = dir.mkdirs()
            if (creado) {
                consola.mostrar("Ruta $ruta creada")
            } else {
                consola.mostrarError("No se pudo crear la ruta $ruta")
                return false
            }
        }
        return true
    }

    fun mostrarUltimoLog(ruta: String) {
        if (!asegurarDirectorio(ruta)) return

        val archivos = mutableListOf<File>()
        val listaArchivos = File(ruta).listFiles()

        if (listaArchivos != null) {
            for (archivo in listaArchivos) {
                if (archivo.isFile) {
                    archivos.add(archivo)
                }
            }
        }

        if (archivos.isEmpty()) {
            consola.mostrar("No existen ficheros de Log")
            return
        }

        var ultimoArchivo = archivos[0]
        for (archivo in archivos) {
            if (archivo.lastModified() > ultimoArchivo.lastModified()) {
                ultimoArchivo = archivo
            }
        }

        consola.mostrar("Mostrando contenido del log más reciente:")

        try {
            val lineas = ficheros.leerArchivo(ultimoArchivo.absolutePath)
            for (linea in lineas) {
                consola.mostrar(linea)
            }
        } catch (e: Exception) {
            consola.mostrarError(e.message ?: "Error desconocido al leer el archivo")
        }
    }

    fun registrarOperacion(rutaLogs: String, contenido: String): Boolean {
        if (!asegurarDirectorio(rutaLogs)) return false

        val timestamp = FechaUtils.obtenerFechaParaNombre()
        val nombreArchivo = "log$timestamp.txt"
        val rutaCompleta = "$rutaLogs/$nombreArchivo"

        return try {
            ficheros.agregarLinea(rutaCompleta, contenido)
            consola.mostrar("Operación registrada en el log: $rutaCompleta")
            true
        } catch (e: Exception) {
            consola.mostrarError("Error guardando log: ${e.message}")
            false
        }
    }
}

