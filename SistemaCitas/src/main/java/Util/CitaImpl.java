package Util;

import GUI.frmRegistro;
import Strategy.GestorCitas;
import static Strategy.ModoFormulario.*;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;


public interface CitaImpl extends Formato {

    
    default void FormatoTablaCitasCalendario(JTable table){
        
        FormatearTablaCitas(table);
        
//        int ancho = 0;
//        int alto = 800;
//        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(00);
        columModel.getColumn(1).setPreferredWidth(40);
        columModel.getColumn(2).setPreferredWidth(150);
        columModel.getColumn(3).setPreferredWidth(150);
        columModel.getColumn(4).setPreferredWidth(50);
        columModel.getColumn(5).setPreferredWidth(50);

//        
//        for (int i = 0; i <5; i++) {
//             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
//        }
//        
//        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
    
    default void FormatoTablaCitasCalendarioPorObstetra(JTable table){
        
        FormatearTablaCitas(table);
        
//        int ancho = 0;
//        int alto = 800;
//        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(00);
        columModel.getColumn(1).setPreferredWidth(40);
        columModel.getColumn(2).setPreferredWidth(150);
        columModel.getColumn(3).setPreferredWidth(150);
        columModel.getColumn(4).setPreferredWidth(50);
        

//        
//        for (int i = 0; i <5; i++) {
//             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
//        }
//        
//        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
    
        default void FormatoTablaDetalleCitas(JTable table){
        
        FormatearTablaCitas(table);
        
//        int ancho = 0;
//        int alto = 800;
//        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(00);
        columModel.getColumn(1).setPreferredWidth(250);
        columModel.getColumn(2).setPreferredWidth(250);
        columModel.getColumn(3).setPreferredWidth(200);
        columModel.getColumn(4).setPreferredWidth(200);
        columModel.getColumn(5).setPreferredWidth(250);
        columModel.getColumn(6).setPreferredWidth(100);
        

//        
//        for (int i = 0; i <5; i++) {
//             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
//        }
//        
//        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
    
}
