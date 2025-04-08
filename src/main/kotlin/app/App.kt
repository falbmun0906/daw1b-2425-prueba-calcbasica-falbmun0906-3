package org.example.app

import modelo.Calculadora
import org.example.ui.Consola
import java.security.KeyStore.TrustedCertificateEntry

class App(val consola: Consola) {
    private val calculadora = Calculadora()

    fun iniciar() {
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
