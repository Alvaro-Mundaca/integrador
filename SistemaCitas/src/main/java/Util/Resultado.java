package Util;

import javax.swing.JOptionPane;


public interface Resultado {

    default void ResultadoRegistro(int Resultado){
        if(Resultado == 1){
            MensajeRegistrar();
        } else {
            MensajeError();
        }
    }
    
    default void ResultadoEliminar(int Resultado){
        if(Resultado == 1){               
            MensajeEliminar();
        } else {               
            MensajeError();
        }
    }   
        
    default void ResultadoActualizar(int Resultado){
        if(Resultado == 1){               
            MensajeActualizar();
        } else {               
            MensajeError();
        }
    }
    
    default void ResultadoRealizar(int Resultado){
        if(Resultado == 1){               
            MensajeRealizar();
        } else {               
            MensajeError();
        }
    } 
    
    
    default void MensajeRegistrar(){        
        JOptionPane.showMessageDialog(null, 
        "REGISTRADO correctamente!",
        "REGISTRO EXITOSO", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    default void MensajeActualizar(){
        JOptionPane.showMessageDialog(null, 
        "ACTUALIZADO correctamente!",
        "ACTUALIZACIÓN EXITOSA", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    default void MensajeRealizar(){
        String nombre = this.getClass().getSimpleName();
        
        JOptionPane.showMessageDialog(null, 
        nombre.substring(3) + " REALIZADO correctamente!",
        "OPERACIÓN EXITOSA", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
    default void MensajeEliminar(){        
        JOptionPane.showMessageDialog(null, 
        " ELIMINADO correctamente!",
        "ElIMINACIÓN EXITOSA", 
        JOptionPane.INFORMATION_MESSAGE);
    }
 
    default void MensajeError(){
        JOptionPane.showMessageDialog(null, 
        "Ocurrió un error!",
        "ALERTA", 
        JOptionPane.INFORMATION_MESSAGE);
    }
    
}
