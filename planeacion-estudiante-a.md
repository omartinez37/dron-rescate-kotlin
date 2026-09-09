# Planeación de Cálculos de Vuelo

Este módulo contiene la lógica y el pseudocódigo diseñado para calcular la distancia y los tiempos estimados de vuelo de un dron, aplicando ajustes dinámicos según las condiciones climáticas o de entorno mediante funciones de orden superior.

## 📋 Lógica del Algoritmo

El cálculo se ejecuta siguiendo los siguientes pasos secuenciales:

### 1. Distancia Total
Calcula el trayecto completo considerando el viaje de ida y vuelta.
```text
distanciaTotal = distanciaIda * 2
```

### 2. Tiempo Base
Determina el tiempo estimado sin contratiempos. El dron mantiene una velocidad constante donde recorre **2 km por minuto**.
```text
tiempoBase = distanciaTotal / 2.0
```

### 3. Ajustes de Tiempo (Lambdas)
Funciones anónimas utilizadas para modificar el tiempo base según el escenario:
*   **Lluvia (+20%):** `valor * 1.20`
*   **Emergencia (-10%):** `valor * 0.90`

### 4. Aplicación de Ajuste (Orden Superior)
Función que recibe el tiempo base y la regla de negocio (lambda) correspondiente para aplicar el cambio de manera dinámica:
```text
aplicarAjuste(valorBase, funciónAjuste) -> ejecuta funciónAjuste(valorBase)
```

### 5. Tiempo Final
El sistema evalúa la condición meteorológica u operativa actual (`normal`, `lluvia`, `emergencia`) y asigna el resultado final:

| Condición | Acción / Resultado |
| :--- | :--- |
| **"lluvia"** | `aplicarAjuste(tiempoBase, aumento20%)` |
| **"emergencia"** | `aplicarAjuste(tiempoBase, disminucion10%)` |
| **"normal"** | `tiempoBase` |
