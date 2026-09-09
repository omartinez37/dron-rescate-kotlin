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
   println("=== SIMULADOR DE MISIÓN DE RESCATE ===\n")

   // 1. CAPTURA DE DATOS
   print("Introduzca la distancia del recorrido de ida (km): ")
   val distanciaIda = readlnOrNull()?.toDoubleOrNull() ?: 0.0

   print("Introduzca el peso de la carga (kg): ")
   val pesoCarga = readlnOrNull()?.toDoubleOrNull() ?: 0.0

   print("Introduzca la batería disponible (%): ")
   val bateriaActual = readlnOrNull()?.toDoubleOrNull() ?: 0.0

   print("Introduzca la velocidad del viento (km/h): ")
   val velocidadViento = readlnOrNull()?.toDoubleOrNull() ?: 0.0

   print("Introduzca el tipo de carga (medicina, alimento, equipo): ")
   val tipoCarga = readlnOrNull()?.trim() ?: "medicina"

   print("Introduzca la condición del vuelo (normal, lluvia, emergencia): ")
   val condicionVuelo = readlnOrNull()?.trim() ?: "normal"

   // 2. CÁLCULOS (Módulo Estudiante A)
   val distanciaTotal = calcularDistanciaTotal(distanciaIda)
   val tiempoBase = calcularTiempoBase(distanciaTotal)

   // Pasamos las lambdas a la función de orden superior
   val tiempoFinal = calcularTiempoFinal(
      tiempoBase = tiempoBase,
      condicion = condicionVuelo,
      ajusteLluvia = aumentarVeintePorciento,
      ajusteEmergencia = disminuirDiezPorciento
   )

   // 3. MOSTRAR RESULTADOS
   println("\n--- RESULTADO DE LA EVALUACIÓN ---")
   println("Distancia total: $distanciaTotal km")
   println("Tiempo estimado: $tiempoFinal minutos")

   // (Los cálculos de batería y riesgo los integrará mi compañero)
}

