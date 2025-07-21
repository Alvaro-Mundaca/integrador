package Util;

import javax.swing.JOptionPane;


public interface Alerta {

    default void Alerta (String texto){
        JOptionPane.showMessageDialog(null, 
        texto,
        "ALERTA", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    default boolean  AlertaSeleccion (int id){
        if (id == 0){
            Alerta("Por favor seleccione un elemento!");
            return false;
        } else {
            return true;
        }
    }
    
}
