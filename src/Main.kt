// Calculo de distancia total (ida y regreso)
fun calcularDistanciaTotal(distanciaIda: Double): Double = distanciaIda * 2.0

// Calculo de tiempo base (2 km por minuto)
fun calcularTiempoBase(distanciaTotal: Double): Double = distanciaTotal / 2.0

// Lambdas para calcular porcentajes de ajuste
val aumentarVeintePorciento: (Double) -> Double = { valor -> valor * 1.20 }

val disminuirDiezPorciento: (Double) -> Double = { valor -> valor * 0.90 }

// Función de orden superior que ejecuta la lambda recibida
fun aplicarAjuste(
   valorBase: Double,
   ajuste: (Double) -> Double
): Double = ajuste(valorBase)

// Selección del tiempo final según la condición de vuelo
fun calcularTiempoFinal(
   tiempoBase: Double,
   condicion: String,
   ajusteLluvia: (Double) -> Double,
   ajusteEmergencia: (Double) -> Double
): Double = when (condicion.trim().lowercase()) {
   "lluvia" -> aplicarAjuste(tiempoBase, ajusteLluvia)
   "emergencia" -> aplicarAjuste(tiempoBase, ajusteEmergencia)
   else -> tiempoBase
}

fun main() {
   println("SIMULADOR DE MISIÓN DE RESCATE")

   println("Introdusca la distancia del recorrido de ida:")

   println("Introdusca Peso de la carga:")

   println("Introdusca la Batería disponible:")

   println("Introdusca la Velocidad del viento:")

   println("Introdusca Tipo de carga:")

   println("Introdusca la condición del vuelo:")
}

