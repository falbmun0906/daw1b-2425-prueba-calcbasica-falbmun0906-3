package org.example.app

import modelo.Calculadora
import org.example.ui.IEntradaSalida
import org.example.utils.FechaUtils
import org.example.utils.IUtilFicheros

class App(
    private val consola: IEntradaSalida,
    private val ficheros: IUtilFicheros
) {
    private val calculadora = Calculadora()
    private val gestorLogs = GestorLogs(consola, ficheros)

    fun ejecutarConArgumentos(args: Array<String>) {
        when (args.size) {
            0 -> gestorLogs.mostrarUltimoLog("./log")
            1 -> gestorLogs.mostrarUltimoLog(args[0])
            4 -> {
                val rutaLogs = args[0]
                val num1 = args[1].toDoubleOrNull()
                val operador = args[2].firstOrNull()
                val num2 = args[3].toDoubleOrNull()

                if (num1 == null || num2 == null || operador == null) {
                    consola.mostrarError("Argumentos numéricos u operador no válidos.")
                    return
                }

                val resultado = calculadora.calcular(num1, operador, num2)
                val fecha = FechaUtils.obtenerFechaFormateada()

                val logLinea = if (resultado != null) {
                    consola.mostrar("Resultado: $resultado")
                    "$fecha - $num1 $operador $num2 = $resultado"
                } else {
                    consola.mostrarError("Operación no válida o división por cero.")
                    "$fecha - Error en la operación: $num1 $operador $num2"
                }

                gestorLogs.registrarOperacion(rutaLogs, logLinea)
            }
            else -> consola.mostrarError("Número de argumentos inválido.")
        }
    }

    fun iniciarCalculadora() {
        var continuar = true
        while (continuar) {
            val numero1 = consola.pedirDouble("Introduce el primer número: ")
            val operador = consola.pedirChar("Introduce el operador (+, -, *, /): ")
            val numero2 = consola.pedirDouble("Introduce el segundo número: ")

            val resultado = calculadora.calcular(numero1, operador, numero2)
            if (resultado == null) {
                consola.mostrarError("Operación no válida o división por cero.")
            } else {
                consola.mostrar("Resultado: $resultado")
            }

            continuar = consola.preguntarSiNo("¿Desea realizar otro cálculo? (S/N): ")
        }

        consola.mostrar("Saliendo...")
    }
}
