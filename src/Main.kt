fun main() {
   println("SIMULADOR DE MISIÓN DE RESCATE")

   println("Introdusca la distancia del recorrido de ida:")

   println("Introdusca Peso de la carga:")

   println("Introdusca la Batería disponible:")

   println("Introdusca la Velocidad del viento:")

   println("Introdusca Tipo de carga:")

   println("Introdusca la condición del vuelo:")
}

// Calculo de distancia total (ida y regreso)
fun calcularDistanciaTotal(distanciaIda: Double): Double = distanciaIda * 2.0

// Calculo de tiempo base (2 km por minuto)
fun calcularTiempoBase(distanciaTotal: Double): Double = distanciaTotal / 2.0

