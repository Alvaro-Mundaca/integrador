package Util;

import GUI.frmRegistroPrograma;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;


public interface ProgramaImpl extends Formato{

    default void FormatoTablaProgramas(JTable table){
        
        FormatearTabla(table);
        
        int ancho = 0;
        int alto = 800;
        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(0);
        columModel.getColumn(1).setPreferredWidth(200);
        columModel.getColumn(2).setPreferredWidth(400);
        columModel.getColumn(3).setPreferredWidth(100);
        columModel.getColumn(4).setPreferredWidth(250);
        columModel.getColumn(5).setPreferredWidth(100);

        
        for (int i = 0; i <=4; i++) {
             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
        }
        
        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
//    
//    default void ActualizarPrograma(){
//        frmRegistroPrograma f = frmRegistroPrograma.getInstancia();
//        f.lblTitulo.setText("ACTUALIZAR PROGRAMA");
//        f.lblEstado.setVisible(true);
//        f.ckbEstado.setVisible(true);
//        f.btnRegistro.setText("Actualizar");
////        f.LimpiarCampos();
//        f.setVisible(true);
////        f.CargarDatosPrograma();
//    }  
//    
}
