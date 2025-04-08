package modelo

class Calculadora {
    fun calcular(n1: Double, op: Char, n2: Double): Double? {
        return when (op) {
            '+' -> n1 + n2
            '-' -> n1 - n2
            '*' -> n1 * n2
            '/' -> if (n2 != 0.0) n1 / n2 else null
            else -> null
        }
    }
}