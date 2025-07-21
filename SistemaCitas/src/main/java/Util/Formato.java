package Util;

import GUI.frmHome;
import com.toedter.calendar.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.*;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.subway.Subway;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.remixicon.RemixiconAL;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;

public interface Formato{
    
    default DefaultTableModel dtm(String[] cabeceras){
        DefaultTableModel dtm = new DefaultTableModel(null,cabeceras){
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
        };
        return dtm;       
    }
    
    default void FormatearJInternalFrame(JInternalFrame internalFrame){
        frmHome.jdpPrincipal.removeAll();
        try {
            ((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);
            internalFrame.setLocation(0, 0);
            internalFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            System.out.println(ex);
        }
        
        frmHome.jdpPrincipal.add(internalFrame);
        internalFrame.setVisible(true);
    }
    
    default void FormatearTabla(JTable table){
        //header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
        
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        //altura de celdas
        table.setRowHeight(30);
             
        //Formato de columnas
        TableColumnModel columModel = table.getColumnModel();     
        
        for (int i = 2; i < columModel.getColumnCount(); i++) {
            TableColumn column = columModel.getColumn(i);
            column.setCellRenderer(cellRender);
            
        }
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));

    }
    
    default void FormatearTablaCustom(JTable table,int index, int h, int f, int c){
        //header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, h));
        header.setReorderingAllowed(false);
        
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        //altura de celdas
        table.setRowHeight(c);
             
        //Formato de columnas
        TableColumnModel columModel = table.getColumnModel();     
        
        for (int i = index; i < columModel.getColumnCount(); i++) {
            TableColumn column = columModel.getColumn(i);
            column.setCellRenderer(cellRender);
            
        }
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, f));

    }
    
    default void FormatearTablaDetalleCitas(JTable table){
        //header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
        
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        //altura de celdas
        table.setRowHeight(30);
             
        //Formato de columnas
        TableColumnModel columModel = table.getColumnModel();     
        
        for (int i = 1; i < columModel.getColumnCount(); i++) {
            TableColumn column = columModel.getColumn(i);
            column.setCellRenderer(cellRender);
            
        }
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));

    }
    
    
    default void FormatearTablaCitas(JTable table){
        //header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
        
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        //altura de celdas
        table.setRowHeight(30);
             
        //Formato de columnas
        TableColumnModel columModel = table.getColumnModel();     
        
        for (int i = 1; i < columModel.getColumnCount(); i++) {
            TableColumn column = columModel.getColumn(i);
            column.setCellRenderer(cellRender);
            
        }
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));

    }
    
    default void FormatearTablaHorarios(JTable table){
        //header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
        
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
        cellRender.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        //altura de celdas
        table.setRowHeight(30);
             
        //Formato de columnas
        TableColumnModel columModel = table.getColumnModel();     
        
        for (int i = 0; i < columModel.getColumnCount(); i++) {
            TableColumn column = columModel.getColumn(i);
            column.setCellRenderer(cellRender);
            
        }
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }
        
//        columModel.removeColumn(columModel.getColumn(0));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));

    }
    
    default void FormatearBotonesCRUD(JButton del, JButton add, JButton edi,JButton expo){
        FontIcon delete = FontIcon.of(Elusive.TRASH_ALT,25,Color.RED);
        FontIcon addon = FontIcon.of(Subway.ADD,23,new Color(0, 200, 0));
        FontIcon edit = FontIcon.of(RemixiconAL.EDIT_CIRCLE_FILL,23,new Color(255, 204, 51));
        FontIcon exp = FontIcon.of(Elusive.FILE_ALT,25,Color.BLUE);


        del.setIcon(delete);
        del.setIconTextGap(8);
        
        add.setIcon(addon);
        add.setIconTextGap(8);
            
        edi.setIcon(edit);
        edi.setIconTextGap(8);
        
        expo.setIcon(exp);
        expo.setIconTextGap(8);
        
    }
    
    default void FormatearBotonesFlecha(JButton go, JButton back){
        FontIcon adelante = FontIcon.of(AntDesignIconsOutlined.ARROW_RIGHT,25,Color.BLACK);
        FontIcon atras = FontIcon.of(AntDesignIconsOutlined.ARROW_LEFT,25,Color.BLACK);
        
        go.setIcon(adelante);  
        back.setIcon(atras);     
    }
    
    default void FormatearComboBox(JComboBox combo){
   
        combo.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus){
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setHorizontalTextPosition(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

                return label;
            }
        });
    }
    
    default void FormatearDateChooser(JDateChooser chooser){  
        JTextField t = ((JTextField) chooser.getDateEditor().getUiComponent());
        t.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    default void FormatearYearChooser(JYearChooser chooser){
        JSpinner spinner = (JSpinner) chooser.getSpinner();
        JTextField text = ((JTextField) spinner.getEditor());
        text.setHorizontalAlignment(SwingConstants.CENTER);       
    }
    
    default void FormatoTelefono (JTextField textField, KeyEvent evt){      
        
        char c = evt.getKeyChar();

        if (!(textField.getText().length() >= 9)) {
            if (!(Character.isDigit(c))) {
                evt.consume();
            }
        } else {
            evt.consume();
        }  
      
    }
        
    default void FormatoDni (JTextField textField, java.awt.event.KeyEvent evt){      
        
        char c = evt.getKeyChar();

        if (!(textField.getText().length() >= 8)) {
            if (!(Character.isDigit(c))) {
                evt.consume();
            }
        } else {
            evt.consume();
        } 
        
    }
    
    default void FormatoBtnCerrar(JButton btn,Color color){
        FontIcon icon = FontIcon.of(Material2AL.CLOSE, 20, color);
        
        if (color.equals(Color.gray)) {
            btn.setIcon(icon);
            btn.setBackground(Color.white);
        } else {
            btn.setIcon(icon);
            btn.setBackground(Color.red);
        }
    }
    
    default void FormatearBusqueda(JTextField textField, String texto){
        
        if (textField.isFocusOwner()) {
            textField.setText("");
            textField.setForeground(Color.black);
            textField.setFont(new Font("Segoe UI", 0, 16));
        } else {
            textField.setText(texto);
            textField.setForeground(Color.gray);
            textField.setFont(new Font("Segoe UI", 2, 16));
        }
        
    }
                
    default int SeleccionarID(MouseEvent evt, JTable table){
        int seleccionarFila = table.rowAtPoint(evt.getPoint());
        String idSeleccionado = String.valueOf(table.getModel().getValueAt(seleccionarFila, 0));
        int id = Integer.parseInt(idSeleccionado);
        return id;
    }          
        
}
