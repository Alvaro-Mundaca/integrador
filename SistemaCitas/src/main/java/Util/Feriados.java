package Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Feriados {

    private static final Map<LocalDate, String> feriados = new HashMap<>();

    public static void cargarFeriados(int year) {
        feriados.clear();

        feriados.put(LocalDate.of(year, 1, 1), "Año Nuevo");
        feriados.put(LocalDate.of(year, 4, 17), "Jueves Santo");
        feriados.put(LocalDate.of(year, 4, 18), "Viernes Santo");
        feriados.put(LocalDate.of(year, 5, 1), "Día del Trabajo");
        feriados.put(LocalDate.of(year, 6, 7), "Día de la Bandera");
        feriados.put(LocalDate.of(year, 6, 29), "San Pedro y San Pablo");
        feriados.put(LocalDate.of(year, 7, 23), "Día de la Fuerza Aérea del Perú");
        feriados.put(LocalDate.of(year, 7, 28), "Fiestas Patrias");
        feriados.put(LocalDate.of(year, 7, 29), "Fiestas Patrias");
        feriados.put(LocalDate.of(year, 8, 6), "Batalla de Junín");
        feriados.put(LocalDate.of(year, 8, 30), "Santa Rosa de Lima");
        feriados.put(LocalDate.of(year, 10, 8), "Combate de Angamos");
        feriados.put(LocalDate.of(year, 11, 1), "Todos los Santos");
        feriados.put(LocalDate.of(year, 12, 8), "Inmaculada Concepción");
        feriados.put(LocalDate.of(year, 12, 9), "Batalla de Ayacucho");
        feriados.put(LocalDate.of(year, 12, 25), "Navidad");
    }

    public static boolean esFeriado(LocalDate fecha) {
        return feriados.containsKey(fecha);
    }

    public static String obtenerNombreFeriado(LocalDate fecha) {
        return feriados.getOrDefault(fecha, "");
    }
} 