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

val clasificarViento: (Double) -> String = { velocidad ->
   when {
      velocidad < 20.0 -> "Bajo"
      velocidad < 40.0 -> "Medio"
      else -> "Alto"
   }
}

fun evaluarRiesgo(
   velocidadViento: Double,
   pesoCarga: Double,
   clasificador: (Double) -> String
): String {
   val riesgoBase = clasificador(velocidadViento)

   return if (pesoCarga > 5.0) {
      when (riesgoBase) {
         "Bajo" -> "Medio"
         "Medio" -> "Alto"
         else -> "Alto"
      }
   } else {
      riesgoBase
   }
}