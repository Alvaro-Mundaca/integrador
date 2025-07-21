package Util;

import java.time.LocalDate;
import java.util.Locale;


public interface HorarioHelper {

     
    default String[] AsignarFechaEncabezadosSemanal(LocalDate fechaBase){
        String[] CabecerasSemanal = new String[7];
        
        for (int i = 0; i < 7; i++) {
            LocalDate fechaSeleccionada = fechaBase.plusDays(i);
            String diaSemana = fechaSeleccionada.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es")).toUpperCase();
            CabecerasSemanal[i] = diaSemana + " " + fechaSeleccionada.getDayOfMonth();
            
        }
        return CabecerasSemanal;
    }    
    
    
    default String[] AsignarFechaEncabezadoDiario(LocalDate fechaBase){
        String[] CabecerasDiario = new String[1];
        String diaSemana = fechaBase.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es")).toUpperCase();
        CabecerasDiario[0] = diaSemana + " " + fechaBase.getDayOfMonth();

        return CabecerasDiario;
    }    
    
}
