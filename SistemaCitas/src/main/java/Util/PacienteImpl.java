package Util;

import GUI.frmRegistro;
import Strategy.GestorPaciente;
import static Strategy.ModoFormulario.*;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;


public interface PacienteImpl extends Formato {

    
    default void FormatoTablaPacientes(JTable table){
        
        FormatearTabla(table);
        
        int ancho = 0;
        int alto = 800;
        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(0);
        columModel.getColumn(1).setPreferredWidth(350);
        columModel.getColumn(2).setPreferredWidth(100);
        columModel.getColumn(3).setPreferredWidth(110);
        columModel.getColumn(4).setPreferredWidth(100);
        columModel.getColumn(5).setPreferredWidth(350);
        columModel.getColumn(6).setPreferredWidth(100);
        columModel.getColumn(7).setPreferredWidth(100);
        for (int i = 0; i <=5; i++) {
             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
        }
        
        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
    
    
}
