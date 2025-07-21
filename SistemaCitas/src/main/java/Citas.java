

import DAO.ObstetraDAO;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.DayOfWeek;
import static java.time.Instant.now;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import Util.Formato;
import java.awt.HeadlessException;


public class Citas extends JFrame implements Formato{
//
    private String[] CabecerasSemanal = new String[7];
    private String[] cabecera = {"HORA","ESTADO"};
    private String[] CabeceraDiario = new String[1];
    private LocalTime hora;
    private LocalDate fecha;
    
    
    private LocalDate ObtenerFecha() {
        
        if (jdchFecha.getDate() != null) {
           fecha = jdchFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           
           return fecha.with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }
         return LocalDate.now().with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
         
    }
    
    
    private LocalDate ObtenerFechaDiario(){
        
        if (jdchFecha1.getDate() != null) {
           fecha = jdchFecha1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           
           return fecha;
        }
       
         return LocalDate.now();
         
    }
    
    private void AsignarFechaEncabezadosDiario(LocalDate fechaBase){
        String diaSemana = fechaBase.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es")).toUpperCase();
        CabeceraDiario[0] = diaSemana + " " + fechaBase.getDayOfMonth();

        
        DefaultTableModel dtm = new DefaultTableModel(null, CabeceraDiario);
        jtEncabezado.setModel(dtm);
        
        jtEncabezado.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) jtEncabezado.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void AsignarFechaEncabezadosSemanal(LocalDate fechaBase){
        
        for (int i = 0; i < 7; i++) {
            LocalDate fechaSeleccionada = fechaBase.plusDays(i);
            String diaSemana = fechaSeleccionada.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es")).toUpperCase();
            CabecerasSemanal[i] = diaSemana + " " + fechaSeleccionada.getDayOfMonth();
            
        }
        
    }    
    
    private void asignarColor(JCalendar calendar) {
        JDayChooser dayChooser = calendar.getDayChooser();

        // Obtiene todos los botones (son los días del mes)
        Component[] botones = dayChooser.getDayPanel().getComponents();

        for (Component c : botones) {
            if (c instanceof JButton) {
                JButton botonDia = (JButton) c;

                try {
                    int dia = Integer.parseInt(botonDia.getText());

                    // Ejemplo: colorear el día 5 de amarillo y el 10 de cyan
                    if (dia == 5) {
                        botonDia.setBackground(Color.YELLOW);
                    } else if (dia == 10) {
                        botonDia.setBackground(Color.CYAN);
                    } else {
                        botonDia.setBackground(Color.WHITE);
                    }
                } catch (NumberFormatException e) {
                    // Algunos botones pueden estar vacíos (p. ej. relleno)
                    botonDia.setBackground(Color.WHITE);
                }
            }
        }
    }

    
    private void ConfigurarTablaSemanal(){
        
        DefaultTableModel dtm = new DefaultTableModel(null,CabecerasSemanal);
            
        //llena las filas con las horas, desde las 8am hasta las 8pm
        if(cbmTurno.getSelectedIndex()==0){
            for (int i = 8; i <= 13; i++) {
            hora = LocalTime.of(i, 0);
            dtm.addRow(new Object[]{hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString()});
            
            }
        } else {
            for (int i = 14; i <= 19; i++) {
            hora = LocalTime.of(i, 0);
            dtm.addRow(new Object[]{hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString(),hora.toString()});
            
            }
        }
     
        //asigna el modelo a la tabla y establece la altura de las celdas
        
        jtSemana.setModel(dtm);
        FormatearTabla(jtSemana);
        
    }
    
    private void CargarObstetras(){
        ObstetraDAO dao = new ObstetraDAO();
        dao.ObtenerObstetrasComboBox(cbmObstetra);
    }
    
    private void ConfigurarTablaDiario(){
        
        DefaultTableModel dtm = new DefaultTableModel(null,cabecera);
            
        //llena las filas con las horas, desde las 8am hasta las 8pm
        if(cbmTurno1.getSelectedIndex()==0){
            for (int i = 8; i <= 13; i++) {
            hora = LocalTime.of(i, 0);
            dtm.addRow(new Object[]{hora});
            
            }
        } else {
            for (int i = 14; i <= 19; i++) {
            hora = LocalTime.of(i, 0);
            dtm.addRow(new Object[]{hora});
            
            }
        }
     
        //asigna el modelo a la tabla y establece la altura de las celdas
        jtDiario.setModel(dtm);
        FormatearTabla(jtDiario);
    }

    public Citas(){
        initComponents();
        LocalDate fechaBase = LocalDate.now();

        AsignarFechaEncabezadosSemanal(fechaBase);
        ConfigurarTablaSemanal();
        CargarObstetras();
//        jdchFecha.setDate(Date.from(now()));
        
    }
    
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtSemana = new javax.swing.JTable();
        cbmTurno = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdchFecha = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jdchFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtEncabezado = new javax.swing.JTable();
        cbmTurno1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtDiario = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cbmObstetra = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jScrollPane1.setDoubleBuffered(true);

        jtSemana.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jtSemana.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, "1", null, null, null},
                {null, null, null, "2", null, null, null},
                {null, null, null, "3", null, null, null},
                {null, null, null, "4", null, null, null},
                {null, null, null, "5", null, null, null}
            },
            new String [] {
                "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
            }
        ));
        jtSemana.setDoubleBuffered(true);
        jtSemana.setEditingColumn(0);
        jtSemana.setEditingRow(0);
        jtSemana.setMinimumSize(new java.awt.Dimension(2100, 500));
        jtSemana.setName(""); // NOI18N
        jtSemana.setRowHeight(30);
        jScrollPane1.setViewportView(jtSemana);

        cbmTurno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbmTurno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAÑANA", "TARDE" }));
        cbmTurno.setDoubleBuffered(true);
        cbmTurno.setOpaque(true);
        cbmTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmTurnoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("TURNO");
        jLabel1.setDoubleBuffered(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("SELECCIONAR FECHA");
        jLabel2.setDoubleBuffered(true);

        jdchFecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jdchFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdchFechaPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jdchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbmTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbmTurno)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdchFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(793, 793, 793))
        );

        jTabbedPane1.addTab("SEMANAL", jPanel1);

        jPanel2.setMaximumSize(new java.awt.Dimension(392, 215));
        jPanel2.setMinimumSize(new java.awt.Dimension(392, 215));
        jPanel2.setName(""); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdchFecha1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jdchFecha1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdchFecha1PropertyChange(evt);
            }
        });
        jPanel2.add(jdchFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 22, 162, 26));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("SELECCIONAR FECHA");
        jLabel4.setDoubleBuffered(true);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 22, -1, 26));

        jScrollPane3.setDoubleBuffered(true);

        jtEncabezado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jtEncabezado.setDoubleBuffered(true);
        jScrollPane3.setViewportView(jtEncabezado);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 318, 30));

        cbmTurno1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbmTurno1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MAÑANA", "TARDE" }));
        cbmTurno1.setDoubleBuffered(true);
        cbmTurno1.setOpaque(true);
        cbmTurno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmTurno1ActionPerformed(evt);
            }
        });
        jPanel2.add(cbmTurno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 22, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("TURNO");
        jLabel3.setDoubleBuffered(true);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 22, 51, 26));

        jScrollPane2.setDoubleBuffered(true);

        jtDiario.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jtDiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, "1", null, null, null},
                {null, null, null, "2", null, null, null},
                {null, null, null, "3", null, null, null},
                {null, null, null, "4", null, null, null},
                {null, null, null, "5", null, null, null}
            },
            new String [] {
                "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
            }
        ));
        jtDiario.setDoubleBuffered(true);
        jtDiario.setEditingColumn(0);
        jtDiario.setEditingRow(0);
        jtDiario.setMinimumSize(new java.awt.Dimension(2100, 500));
        jtDiario.setName(""); // NOI18N
        jtDiario.setRowHeight(30);
        jScrollPane2.setViewportView(jtDiario);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 318, 214));

        jTabbedPane1.addTab("DIARIO", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "dia"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 900, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("MENSUAL", jPanel4);

        cbmObstetra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbmObstetra.setDoubleBuffered(true);
        cbmObstetra.setOpaque(true);
        cbmObstetra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmObstetraActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("BUSCAR OBSTETRA");
        jLabel5.setDoubleBuffered(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbmObstetra, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmObstetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbmObstetraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmObstetraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmObstetraActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        ObtenerFechaDiario();
        AsignarFechaEncabezadosDiario(ObtenerFechaDiario());
                ConfigurarTablaDiario();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void cbmTurno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmTurno1ActionPerformed
        //        ConfigurarTablaDiario();
    }//GEN-LAST:event_cbmTurno1ActionPerformed

    private void jdchFecha1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdchFecha1PropertyChange
        LocalDate fechaBase = ObtenerFechaDiario();
        AsignarFechaEncabezadosDiario(fechaBase);
        //        ConfigurarTablaDiario();
    }//GEN-LAST:event_jdchFecha1PropertyChange

    private void jdchFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdchFechaPropertyChange
        ObtenerFecha();
        AsignarFechaEncabezadosSemanal(ObtenerFecha());
        ConfigurarTablaSemanal();
    }//GEN-LAST:event_jdchFechaPropertyChange

    private void cbmTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmTurnoActionPerformed
        ConfigurarTablaSemanal();
    }//GEN-LAST:event_cbmTurnoActionPerformed

public static void main(String[] args) {

    try {
        UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
    } catch (Exception e) {
        e.printStackTrace();
    }

    java.awt.EventQueue.invokeLater(() -> {
        new Citas().setVisible(true);
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbmObstetra;
    private javax.swing.JComboBox<String> cbmTurno;
    private javax.swing.JComboBox<String> cbmTurno1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser jdchFecha;
    private com.toedter.calendar.JDateChooser jdchFecha1;
    private javax.swing.JTable jtDiario;
    private javax.swing.JTable jtEncabezado;
    private javax.swing.JTable jtSemana;
    // End of variables declaration//GEN-END:variables
}
