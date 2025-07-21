package Util;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;


public interface ObstetraImpl extends Formato{

    default void FormatoTablaObstetras(JTable table){
        
        FormatearTabla(table);
        
        int ancho = 0;
        int alto = 800;
        
        TableColumnModel columModel = table.getColumnModel();
        columModel.getColumn(0).setPreferredWidth(0);
        columModel.getColumn(1).setPreferredWidth(350);
        columModel.getColumn(2).setPreferredWidth(100);
        columModel.getColumn(3).setPreferredWidth(110);
        columModel.getColumn(4).setPreferredWidth(300);
        columModel.getColumn(5).setPreferredWidth(100);
        columModel.getColumn(6).setPreferredWidth(110);
        columModel.getColumn(7).setPreferredWidth(110);
        columModel.getColumn(8).setPreferredWidth(110);
        columModel.getColumn(9).setPreferredWidth(80);
        
        for (int i = 0; i <=5; i++) {
             ancho = ancho + columModel.getColumn(i).getPreferredWidth();
        }
        
        table.getParent().getParent().setPreferredSize(new Dimension(ancho, alto));
        columModel.removeColumn(columModel.getColumn(0));
       
    }
    
}
