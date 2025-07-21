package GUI;

import Util.Feriados;
import Util.Formato;
import Util.RenderDia;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.toedter.calendar.JYearChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;

public class test extends JFrame implements Formato{

    private String[] cabeceras = {"LUNES","MARTES","MIÉRCOLES","JUEVES","VIERNES","SÁBADO","DOMINGO"};
    private String[] meses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
    
    public static int año, mes;
    Object[] dia = new Object[7];   
    LocalDate hoy = LocalDate.now();
    boolean box = false;
    
    private void cargarMes(){
        for (String mes : meses) {
            boxMes.addItem(mes);
        }
    }
    
    private void seleccionarFecha(){
        mes = boxMes.getSelectedIndex()+1;
        año = jycAño.getValue();
    }
    
    private void cargarDatos() {
        seleccionarFecha();
        Feriados.cargarFeriados(año);
        
        jTable1.setDefaultRenderer(Object.class, new RenderDia());
        DefaultTableModel dtm = new DefaultTableModel(null, cabeceras){
        @Override
        public boolean isCellEditable(int row, int column) { return false; } 
        };
        
        YearMonth ym = YearMonth.of(año, mes);
        int dias = ym.lengthOfMonth();
        LocalDate primerDia = LocalDate.of(año, mes, 1);
        int inicio = primerDia.getDayOfWeek().getValue() - 1; // Lunes=0 ... Domingo=6

        int contador = 1;
        for (int fila = 0; fila < 6; fila++) {
            Object[] dia = new Object[7];
            for (int col = 0; col < 7; col++) {
                if (fila == 0 && col < inicio) {
                    dia[col] = null;
                } else if (contador <= dias) {
                    dia[col] = contador++;
                } else {
                    dia[col] = null;
                }
            }
            dtm.addRow(dia);
        }
        jTable1.setModel(dtm);
        jTable1.clearSelection();
        jTable1.setRowHeight(80);
        
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
    }

    private void ObtenerFecha(){
        int fila = jTable1.getSelectedRow();
        int columna = jTable1.getSelectedColumn();
        
        if (fila < 0 || columna < 0) {
            return;  // No hay selección válida, salimos
        }
        
        Object diaSeleccionado = jTable1.getValueAt(fila, columna);
        
        if(diaSeleccionado != null){
            int dia = Integer.parseInt(diaSeleccionado.toString());
            LocalDate fechaSeleccionada = LocalDate.of(año, mes, dia);
        
            System.out.println(fechaSeleccionada);
        } else {
            System.out.println("celda vacia!");
        }
    }
    
    private void configurarListeners() { 
        jycAño.addPropertyChangeListener("year", evt -> {
            // evt.getNewValue() viene como Integer con el nuevo año
            año = (Integer) evt.getNewValue();
            cargarDatos();
        });
        
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObtenerFecha();
            }});

        // Listener para cambios de selección de columna
        jTable1.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObtenerFecha();
            }});
    }
    
    private void actualizarBoxes(){
        box = true;
        boxMes.setSelectedIndex(mes-1);
        jycAño.setValue(año);
        box = false;
    }
    
    private void AvanzarMes(){
        if (boxMes.getSelectedIndex() == 11) {
            boxMes.setSelectedIndex(0);
            jycAño.setValue(jycAño.getValue()+1);
        } else {
            boxMes.setSelectedIndex(boxMes.getSelectedIndex()+1);
        }   
    }
    
    private void AtrasMes(){
        if (boxMes.getSelectedIndex() == 0) {
            boxMes.setSelectedIndex(11);
            jycAño.setValue(jycAño.getValue()-1);
        } else {
            boxMes.setSelectedIndex(boxMes.getSelectedIndex()-1);
        }  
    }
    
    private void ConfigurarYearChooser(){
        FormatearYearChooser(jycAño);
        
        JSpinner spinner = (JSpinner) jycAño.getSpinner();
        spinner.setBorder(null);
        
        spinner.setBorder(BorderFactory.createEmptyBorder());

        for (Component comp : spinner.getComponents()) {
            if (comp instanceof JButton boton) {
                boton.setBorder(BorderFactory.createEmptyBorder());
                boton.setFocusPainted(false);
                boton.setContentAreaFilled(false);
                boton.setOpaque(false);
            }
        }
        
        for (Component c : spinner.getComponents()) {
            if (c instanceof JTextField) {
                ((JTextField)c).setEditable(false);
                ((JTextField)c).setFocusable(false);
                ((JTextField)c).setBorder(null);

            }
        }
    }
    
    
    public test() {
        initComponents();
        FormatearBotonesFlecha(btnAdelante, btnAtras);
        ConfigurarYearChooser();
        FormatearComboBox(boxMes);
        cargarMes();
        configurarListeners();
        mes = hoy.getMonthValue();
        año = hoy.getYear();
        actualizarBoxes();
        cargarDatos();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        base = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        boxMes = new javax.swing.JComboBox<>();
        jycAño = new com.toedter.calendar.JYearChooser();
        btnAdelante = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setFocusable(false);
        jTable1.setSelectionBackground(new java.awt.Color(64, 164, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));

        boxMes.setBackground(new java.awt.Color(255, 204, 0));
        boxMes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        boxMes.setBorder(null);
        boxMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxMesItemStateChanged(evt);
            }
        });

        jycAño.setBackground(new java.awt.Color(255, 204, 0));
        jycAño.setEndYear(3000);
        jycAño.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jycAño.setRequestFocusEnabled(false);
        jycAño.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jycAñoInputMethodTextChanged(evt);
            }
        });
        jycAño.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jycAñoPropertyChange(evt);
            }
        });

        btnAdelante.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdelante.setForeground(new java.awt.Color(255, 255, 255));
        btnAdelante.setToolTipText("");
        btnAdelante.setBorder(null);
        btnAdelante.setBorderPainted(false);
        btnAdelante.setContentAreaFilled(false);
        btnAdelante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdelante.setIconTextGap(0);
        btnAdelante.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAdelante.setPreferredSize(new java.awt.Dimension(32, 32));
        btnAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdelanteActionPerformed(evt);
            }
        });

        btnAtras.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setBorderPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtras.setIconTextGap(0);
        btnAtras.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnAtras.setPreferredSize(new java.awt.Dimension(32, 32));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jycAño, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(btnAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jycAño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdelante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(boxMes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout baseLayout = new javax.swing.GroupLayout(base);
        base.setLayout(baseLayout);
        baseLayout.setHorizontalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        baseLayout.setVerticalGroup(
            baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(baseLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(base, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void boxMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxMesItemStateChanged
        cargarDatos();
    }//GEN-LAST:event_boxMesItemStateChanged
        
    private void btnAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdelanteActionPerformed
        AvanzarMes();
    }//GEN-LAST:event_btnAdelanteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        AtrasMes();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void jycAñoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jycAñoPropertyChange

    }//GEN-LAST:event_jycAñoPropertyChange

    private void jycAñoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jycAñoInputMethodTextChanged

    }//GEN-LAST:event_jycAñoInputMethodTextChanged

    public static void main(String args[]) {
        try {
           Color amarillo = new Color(255, 204, 0);
           UIManager.setLookAndFeel(new FlatArcIJTheme());
           UIManager.put("Spinner.border", BorderFactory.createEmptyBorder());
           UIManager.put("ComboBox.background", amarillo);
           UIManager.put("ComboBox.buttonBackground", amarillo);
           UIManager.put("ComboBox.foreground", Color.BLACK);
           UIManager.put("Spinner.background",       amarillo);
           UIManager.put("Spinner.foreground",       Color.BLACK);
           UIManager.put("Spinner.buttonBackground", amarillo);
          new test().setVisible(true);
        } catch (Exception e) {
           e.printStackTrace();
        }
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel base;
    private javax.swing.JComboBox<String> boxMes;
    private javax.swing.JButton btnAdelante;
    private javax.swing.JButton btnAtras;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JYearChooser jycAño;
    // End of variables declaration//GEN-END:variables

}
