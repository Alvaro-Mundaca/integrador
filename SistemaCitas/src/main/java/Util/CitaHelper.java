package Util;

import javax.swing.JOptionPane;


public interface CitaHelper {

        default boolean ConfirmarRegistroCita(String texto){
        Object[] options = {"Si", "Cancelar"};
        
        int jop = JOptionPane.showOptionDialog(null,
                "Desea REGISTRAR la cita con los siguientes datos? \n \n" + texto,
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
    
}
