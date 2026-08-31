# Planeación de Seguridad de la Misión

Este módulo contiene la lógica y el pseudocódigo diseñado para calcular el consumo de batería, evaluar la reserva mínima y determinar el nivel de riesgo de una misión, aplicando ajustes dinámicos según el peso de la carga y la velocidad del viento mediante funciones de orden superior.

## 📋 Lógica del Algoritmo

El cálculo se ejecuta siguiendo los siguientes pasos secuenciales:

### 1. Consumo de Batería

Calcula el porcentaje de batería que se consumirá, combinando el efecto de la distancia, el peso de la carga y un extra opcional (lluvia/emergencia).

```
consumoBateria = (distanciaTotal * 4) + (pesoCarga * 2) + consumoExtra
```

`consumoExtra` tiene un valor predeterminado de `0.0` y se sobreescribe con argumentos con nombre cuando hay lluvia (+10) o emergencia (+5).

**Firma:**
```kotlin
fun calcularConsumoBateria(
    distanciaTotal: Double,
    pesoCarga: Double,
    consumoExtra: Double = 0.0
): Double
```

**Casos de prueba:**

| Caso | distanciaTotal | pesoCarga | consumoExtra | Resultado esperado |
|---|---|---|---|---|
| Normal | 8.0 | 2.5 | 0.0 | 37.0 |
| Límite | 0.0 | 0.0 | 0.0 | 0.0 |

---

### 2. Batería Final

Determina cuánta batería queda disponible al terminar el vuelo, restando el consumo a la batería inicial.

```
bateriaFinal = bateriaInicial - consumo
```

**Firma:**
```kotlin
fun calcularBateriaFinal(
    bateriaInicial: Double,
    consumo: Double
): Double = TODO()
```

**Casos de prueba:**

| Caso | bateriaInicial | consumo | Resultado esperado |
|---|---|---|---|
| Normal | 75.0 | 37.0 | 38.0 |
| Límite | 20.0 | 37.0 | -17.0 (batería insuficiente / negativa) |

---

### 3. Validación de Reserva Mínima

Verifica si la batería restante cumple con el mínimo de seguridad exigido (15% por defecto).

```
tieneReservaSuficiente = bateriaFinal >= reservaMinima
```

**Firma:**
```kotlin
fun tieneReservaSuficiente(
    bateriaFinal: Double,
    reservaMinima: Double = 15.0
): Boolean = TODO()
```

**Casos de prueba:**

| Caso | bateriaFinal | reservaMinima | Resultado esperado |
|---|---|---|---|
| Normal | 38.0 | 15.0 | true |
| Límite | 15.0 | 15.0 | true (igualdad cuenta como suficiente) |

---

### 4. Clasificación del Viento (Lambda)

Función anónima utilizada para traducir la velocidad del viento en un nivel de riesgo base:

- Menor que 20 km/h → `"Bajo"`
- Entre 20 y 39 km/h → `"Medio"`
- 40 km/h o más → `"Alto"`

**Firma:**
```kotlin
val clasificarViento: (Double) -> String = { velocidad ->
    // devolver Bajo, Medio o Alto
}
```

**Casos de prueba:**

| Caso | velocidad | Resultado esperado |
|---|---|---|
| Normal | 18.0 | "Bajo" |
| Límite | 20.0 | "Medio" (frontera exacta entre Bajo y Medio) |

---

### 5. Evaluación de Riesgo (Orden Superior)

Función que recibe la velocidad del viento y la regla de clasificación (lambda) correspondiente, y luego ajusta el resultado si la carga es pesada:

```
evaluarRiesgo(velocidadViento, pesoCarga, clasificador) -> 
    riesgoBase = clasificador(velocidadViento)
    si pesoCarga > 5.0 -> sube un nivel el riesgoBase
    si no -> se mantiene riesgoBase
```

**Firma:**
```kotlin
fun evaluarRiesgo(
    velocidadViento: Double,
    pesoCarga: Double,
    clasificador: (Double) -> String
): String
```

| Peso de carga | Riesgo base | Resultado final |
|---|---|---|
| ≤ 5 kg | Bajo / Medio / Alto | Se mantiene igual |
| > 5 kg | Bajo | Medio |
| > 5 kg | Medio | Alto |
| > 5 kg | Alto | Alto (ya es el máximo) |

**Casos de prueba:**

| Caso | velocidadViento | pesoCarga | Resultado esperado |
|---|---|---|---|
| Normal | 18.0 | 2.5 | "Bajo" (sin ajuste, peso ≤ 5kg) |
| Límite | 15.0 | 6.0 | "Medio" (clasifica "Bajo" pero sube un nivel por peso > 5kg) |

---

## 🔀 Flujo de trabajo (Git)

1. Este archivo se agrega en la rama `seguridad-mision`.
2. Va en un **commit propio**, separado del código.
3. Este commit debe aparecer **antes** que cualquier commit con la implementación en Kotlin de estas funciones.
4. Tras este commit, se implementan las funciones respetando exactamente las firmas y reglas aquí documentadas.
