package src

fun calcularConsumoBateria(
   distanciaTotal: Double,
   pesoCarga: Double,
   consumoExtra: Double = 0.0
): Double {
   val consumoPorDistancia = distanciaTotal * 4
   val consumoPorPeso = pesoCarga * 2
   return consumoPorDistancia + consumoPorPeso + consumoExtra
}

fun calcularBateriaFinal(
   bateriaInicial: Double,
   consumo: Double
): Double = bateriaInicial - consumo

fun tieneReservaSuficiente(
   bateriaFinal: Double,
   reservaMinima: Double = 15.0
): Boolean = bateriaFinal >= reservaMinima