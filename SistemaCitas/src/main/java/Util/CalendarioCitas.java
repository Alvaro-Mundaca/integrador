
package Util;

import DAO.CitaDAO;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Map<LocalDate, Map<Programa, Total>>
public class CalendarioCitas {
    public static final Map<LocalDate, Map<String, Integer>> resumenCitasPorDia = new HashMap<>();

    public static void cargarCitasAgrupadas(String obstetraNombreCompleto) {
        resumenCitasPorDia.clear();
        CitaDAO dao = new CitaDAO();
        dao.obtenerResumenCitas(resumenCitasPorDia, obstetraNombreCompleto); // nuevo método
    }

    public static Map<String, Integer> obtenerCitasDelDia(LocalDate fecha) {
        return resumenCitasPorDia.getOrDefault(fecha, new HashMap<>());
    }
}