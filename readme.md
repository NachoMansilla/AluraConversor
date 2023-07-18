# 🌎 Java Currency Converter Challenge 🌍

Bienvenidos al primer desafío del Challenge Java. En este proyecto, hemos desarrollado un convertidor de divisas utilizando el lenguaje Java y con soporte para más de 170+ divisas.

![gif_programa](tuto.gif)




## ✅ Requisitos

El convertidor de moneda incluye las siguientes características:

- 🔄 Convertir de la moneda local a Dólar (USD), Euros (EUR), Libras Esterlinas (GBP), Yen Japonés (JPY) y Won Sul-Coreano (KRW).
- 🔄 Convertir de Dólar (USD), Euros (EUR), Libras Esterlinas (GBP), Yen Japonés (JPY) y Won Sul-Coreano (KRW) a la moneda local.

Además, se incorporan las siguientes funcionalidades:

- 💡 Se consume la API al iniciar el programa y no cada vez que se realiza una conversión.
- 📋 Los combobox cuentan con todas las divisas y un autocompletado.
- ➕ La conversión se realiza a medida que el usuario agrega el valor a convertir, siempre y cuando ya haya seleccionado las divisas de origen y destino.
- 🔁 Existe un botón en el centro para invertir la selección de la divisa.

🚧 Por otro lado, se había planeado incluir una característica adicional que mostraría la bandera de cada divisa en la lista desplegable del combobox. Sin embargo, debido a la complejidad de esta tarea, se decidió no implementarla en esta versión del proyecto.

## 🛠 Tecnologías y bibliotecas utilizadas

- 🖥️ Lenguaje de programación: Java
- 🎨 Interfaz gráfica: JavaFX
- 📝 Estilos: CSS
- 🌐 Consumo de API: librería OkHttp3
- 📦 Conversión de JSON: librería Jackson
- 📐 Patrón de diseño: Singleton

Las bibliotecas mencionadas están en el directorio `src`.

## 🚀 Uso del convertidor de moneda

1. Abra la aplicación.
2. Selecciona la divisa de origen en el primer combobox.
3. Selecciona la divisa de destino en el segundo combobox.
4. Ingresa el valor a convertir.
5. El resultado de la conversión se mostrará automáticamente.
6. Si lo deseas, puedes invertir las divisas de origen y destino con el botón central.

## 🔑 Configuración de la API Key

Para usar tu propia API key de CurrencyAPI, necesitas registrarte en [https://currencyapi.com](https://currencyapi.com). Una vez que hayas obtenido tu clave API, puedes establecerla en las variables de entorno con el nombre `API_KEY` o puedes reemplazarla directamente en el código donde se hace la llamada a la API.

