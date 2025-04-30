package com.app.domain.constants;

public class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String PERCENTAGE_FIELD = "porcentaje";
    public static final String ERROR_500 = "No se pudo obtener el porcentaje y no existe valor cacheado.";
    public static final String ENDPOINT_CALCULO = "/api/calculo";
    public static final String NUM_1 = "num1=";
    public static final String NUM_2 = ", num2=";
    public static final String RESULTADO = "resultado=";
    public static final String RESULTADO_OK = "OK";
    public static final String RESULTADO_CREATE = "CREAR";
    public static final String ERROR = "ERROR";
    public static final String RESULTADO_LISTAR = "LISTAR";
    public static final String RESULTADO_MENSAJE = "Cálculo creado correctamente";
    public static final String RESULTADO_MENSAJE_LISTAR = "Listado de historial correctamente";
}
