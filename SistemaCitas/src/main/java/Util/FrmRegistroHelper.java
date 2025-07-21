package Util;

import Strategy.ModoFormulario;
import Strategy.IGestorEntidad;
import GUI.frmRegistro;
import GUI.frmRegistroCitas;
import GUI.frmRegistroPrograma;
import Strategy.GestorCitas;
import Strategy.GestorPaciente;
import Strategy.GestorPrograma;


public interface FrmRegistroHelper {
    
    default void mostrarFormularioRegistro(IGestorEntidad gestor, ModoFormulario modo) {
        if (gestor instanceof GestorCitas) {
            RegistroCita(gestor, modo);
        }else if (gestor instanceof GestorPrograma) {
            RegistroPrograma(gestor, modo);
        } else {
            RegistroPersona(gestor, modo);
        } 
    }
    
    default void RegistroPersona(IGestorEntidad gestor, ModoFormulario modo){
        frmRegistro.EliminarInstacia();
        
        frmRegistro f = frmRegistro.getInstancia(gestor, modo);

        if (gestor instanceof GestorPaciente) {
            f.lblTituloRegistro.setText(modo == ModoFormulario.REGISTRO ? "REGISTRAR PACIENTE" : "ACTUALIZAR PACIENTE");
        } else {
            f.lblTituloRegistro.setText(modo == ModoFormulario.REGISTRO ? "REGISTRAR OBSTETRA" : "ACTUALIZAR OBSTETRA");
        }
        
        if (modo == ModoFormulario.REGISTRO) {
            f.btnRegistro.setText("Registrar");
            f.lblEstado.setVisible(false);
            f.ckbEstado.setVisible(false);
            f.limpiarCampos();
        } else {
            f.btnRegistro.setText("Actualizar");
            f.lblEstado.setVisible(true);
            f.ckbEstado.setVisible(true);
            if (gestor instanceof GestorPaciente) {
                f.CargarDatosPaciente();
            } else {
                f.CargarDatosObstetra();
            }
        }

        f.setVisible(true);
    }
     
    default void RegistroPrograma(IGestorEntidad gestor, ModoFormulario modo){
        frmRegistroPrograma.EliminarInstacia();
        
        frmRegistroPrograma f = frmRegistroPrograma.getInstancia(gestor, modo);

        f.lblTitulo.setText(modo == ModoFormulario.REGISTRO ? "REGISTRAR PROGRAMA" : "ACTUALIZAR PROGRAMA");
        
        
        if (modo == ModoFormulario.REGISTRO) {
            f.btnRegistro.setText("Registrar");
            f.lblEstado.setVisible(false);
            f.ckbEstado.setVisible(false);
            f.LimpiarCampos();
        } else {
            f.btnRegistro.setText("Actualizar");
            f.lblEstado.setVisible(true);
            f.ckbEstado.setVisible(true);
            f.CargarDatos(); 
        }

        f.setVisible(true);
    }
    
    default void RegistroCita(IGestorEntidad gestor, ModoFormulario modo){
        frmRegistroCitas.EliminarInstacia();
        
        frmRegistroCitas f = frmRegistroCitas.getInstancia(gestor, modo);

        if (modo == ModoFormulario.REGISTRO) {
            f.btnRegistro.setText("Registrar");
            f.lblEstado.setVisible(false);
            f.cbmEstado.setVisible(false);
//            f.limpiarCampos();
        } else {
            f.btnRegistro.setText("Actualizar");
            f.lblEstado.setVisible(true);
            f.cbmEstado.setVisible(true);
//            f.CargarDatos(); 
        }

        f.setVisible(true);
    }
   
}
