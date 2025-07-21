package Util;

import javax.swing.JOptionPane;

public interface Confirmacion {

    default boolean Confirmacion(String texto){
        Object[] options = {"Si", "Cancelar"};
        
        int jop = JOptionPane.showOptionDialog(null,
                texto,
                "CONFIRMACIÓN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
    
    default boolean ConfirmacionCerrarSesion(){
        Object[] options = {"Si", "Cancelar"};
        
        int jop = JOptionPane.showOptionDialog(null,
                "Desea cerrar sesión?",
                "CONFIRMACIÓN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
        
    default boolean ConfirmacionContinuar(){
        Object[] options = {"Si", "Cancelar"};
        String nombre = this.getClass().getSimpleName();
        
        int jop = JOptionPane.showOptionDialog(null,
                "Desea Agregar otro registro?",
                "CONFIRMACIÓN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
    
    default boolean ConfirmacionEliminar(){
        Object[] options = {"Si", "Cancelar"};
        String nombre = this.getClass().getSimpleName();
        
        int jop = JOptionPane.showOptionDialog(null,
                "Desea Eliminar el elemento seleccionado?",
                "CONFIRMACIÓN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
    
    default boolean ConfirmacionActualizar(){
        Object[] options = {"Si", "Cancelar"};
        String nombre = this.getClass().getSimpleName();
        
        int jop = JOptionPane.showOptionDialog(null,
                "Desea Actualizar el elemento seleccionado?",
                "CONFIRMACIÓN",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        
        return jop == JOptionPane.YES_OPTION;
    }
       
}