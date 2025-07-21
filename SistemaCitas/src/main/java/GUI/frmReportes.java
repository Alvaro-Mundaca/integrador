
package GUI;

import DAO.CitaDAO;
import DAO.ObstetraDAO;
import DAO.ProgramaDAO;
import Model.Programa;
import Util.CitaImpl;
import Util.ExportHelper;
import Util.Formato;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class frmReportes extends javax.swing.JInternalFrame implements Formato,CitaImpl{

    String inicio,fin;
    LocalDate hoy = LocalDate.now();
    
    ProgramaDAO programaDAO = new ProgramaDAO();
    ObstetraDAO obstetraDAO = new ObstetraDAO();
    CitaDAO citaDAO = new CitaDAO();
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    ExportHelper export = new ExportHelper();
    
    private void ExportarPDF(){
        if (jTabbedPane1.getSelectedIndex()==0) {
            export.generarPDF_Custom(jtMetasIndividuales, "Metas_Individuales");
        } else {
            export.generarPDF_Custom(jtDetalleCitas, "Detalle_Citas");
        }
    }
    
    public void ExportarExcel() throws IOException{
        if (jTabbedPane1.getSelectedIndex()==0) {
            export.generarExcel_Custom(jtMetasIndividuales, "Metas_Individuales");
        } else {
            export.generarExcel_Custom(jtDetalleCitas, "Detalle_Citas");
        }
    }
    
    private void cargarMetas() {
        jpMetas.setVisible(false);
        lblObstetra.setVisible(false);
        cbmObstetras.setVisible(false);
        btnMetas.setText("Metas Mensuales");
        DefaultTableModel dtm = new DefaultTableModel();
       
        List<Programa> programas = programaDAO.ObstenerMetasPrograma();
        List<String> obstetras = obstetraDAO.ObtenerObstetras();

        int numObstetras = obstetras.size();

        // Cabeceras
        dtm.addColumn("Obstetra");
        for (Programa p : programas) {
            dtm.addColumn(p.getNombre());
        }

        // Filas
        for (String obstetra : obstetras) {
            Vector<Object> fila = new Vector<>();
            fila.add(obstetra);
            for (Programa p : programas) {
                int metaIndividual = (int) Math.ceil((double) p.getMeta() / numObstetras);
                fila.add(metaIndividual);
            }
            dtm.addRow(fila);
        }

        jtMetasIndividuales.setModel(dtm);
        jtMetasIndividuales.setRowHeight(30);
        FormatearTablaCustom(jtMetasIndividuales,1,16,16,30);
    }
      
    private void cargarMetasMensuales() {
        btnMetas.setText("Metas Anuales");
        lblObstetra.setVisible(true);
        cbmObstetras.setVisible(true);
        jpMetas.setVisible(false);
        jCheckBox1.setSelected(false);
        
        DefaultTableModel dtm = new DefaultTableModel();

        List<Programa> programas = programaDAO.ObstenerMetasPrograma();
        List<String> obstetras = obstetraDAO.ObtenerObstetras();
        int numObstetras = obstetras.size();

        // Cabecera: Obstetra, Programa, Enero...Diciembre
        dtm.addColumn("Obstetra");
        dtm.addColumn("Programa");

        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (String mes : meses) {
            dtm.addColumn(mes);
        }

        for (String obstetra : obstetras) {
            for (Programa p : programas) {
                int metaAnual = p.getMeta();
                int metaIndividual = (int) Math.ceil((double) metaAnual / numObstetras);
                int metaMensual = (int) Math.ceil((double) metaIndividual / 12);

                Vector<Object> fila = new Vector<>();
                fila.add(obstetra);
                fila.add(p.getNombre());

                for (int i = 0; i < 12; i++) {
                    fila.add(metaMensual);
                }

                dtm.addRow(fila);
            }
        }

        jtMetasIndividuales.setModel(dtm);
        jtMetasIndividuales.setRowHeight(30);
        FormatearTablaCustom(jtMetasIndividuales, 1, 16, 16, 30);
}
    
    private void cargarTablaMetasProgramas() {
    List<Programa> programas = programaDAO.ObstenerMetasPrograma();

    DefaultTableModel dtm = new DefaultTableModel();

    // Agregar encabezados: cada programa será una columna
    for (Programa p : programas) {
        dtm.addColumn(p.getNombre());
    }

    // Crear la fila con las metas
    Vector<Object> fila = new Vector<>();
    for (Programa p : programas) {
        fila.add(p.getMeta());
    }

    // Agregar la única fila
    dtm.addRow(fila);

    jtMetas.setModel(dtm);
    jtMetas.setRowHeight(20);
    FormatearTablaCustom(jtMetas, 0, 16, 16, 20);
}
    
    private void cargarMetasMensualesFiltradas() {
        DefaultTableModel dtm = new DefaultTableModel();

        List<Programa> programas = programaDAO.ObstenerMetasPrograma();
        List<String> obstetras = obstetraDAO.ObtenerObstetras();
        int numObstetras = obstetras.size();

        String seleccionado = (String) cbmObstetras.getSelectedItem();

        dtm.addColumn("Obstetra");
        dtm.addColumn("Programa");

        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        for (String mes : meses) {
            dtm.addColumn(mes);
        }

        List<String> obstetrasFiltrados = new ArrayList<>();

        if ("Seleccionar Obstetra".equals(seleccionado)) {
            obstetrasFiltrados.addAll(obstetras);
        } else {
            obstetrasFiltrados.add(seleccionado);
        }

        for (String obstetra : obstetrasFiltrados) {
            for (Programa p : programas) {
                int metaAnual = p.getMeta();
                int metaIndividual = (int) Math.ceil((double) metaAnual / numObstetras);
                int metaMensual = (int) Math.ceil((double) metaIndividual / 12);

                Vector<Object> fila = new Vector<>();
                fila.add(obstetra);
                fila.add(p.getNombre());

                for (int i = 0; i < 12; i++) {
                    fila.add(metaMensual);
                }

                dtm.addRow(fila);
            }
        }

        jtMetasIndividuales.setModel(dtm);
        jtMetasIndividuales.setRowHeight(25);
        FormatearTablaCustom(jtMetasIndividuales, 0,16,16,35);
    }
    
    private void capturarFechas(){
         inicio = sdf.format(jdcInicio.getDate());
         fin = sdf.format(jdcFin.getDate());
    }    
    
    private void DetalleCitas(){
        String[] cabeceras = {"ID","PACIENTE","OBSTETRA","FEC. REGISTRO", "FEC. CITA", "DESCRIPCION","ESTADO", "PROGRAMA"};
        DefaultTableModel dtm = new DefaultTableModel(null, cabeceras);
        citaDAO.ListarDetalleCitasPorFecha(dtm, inicio, fin);
        jtDetalleCitas.setModel(dtm);
        FormatoTablaDetalleCitas(jtDetalleCitas);
    }

    public frmReportes() {
        initComponents();
        obstetraDAO.ObtenerObstetrasComboBox(cbmObstetras);
        cargarMetas();
        cargarTablaMetasProgramas();
        jpMetas.setVisible(false);
        inicio = "2025-01-01";
        fin = sdf.format(new Date());
        jdcInicio.setDate(java.sql.Date.valueOf(hoy));
        jdcFin.setDate(java.sql.Date.valueOf(hoy));
        DetalleCitas();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMetasIndividuales = new javax.swing.JTable();
        jpMetas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtMetas = new javax.swing.JTable();
        cbmObstetras = new javax.swing.JComboBox<>();
        lblObstetra = new javax.swing.JLabel();
        btnMetas = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtDetalleCitas = new javax.swing.JTable();
        jdcInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jdcFin = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnExportar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestionar Reportes");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jtMetasIndividuales.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtMetasIndividuales);

        jtMetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jtMetas);

        javax.swing.GroupLayout jpMetasLayout = new javax.swing.GroupLayout(jpMetas);
        jpMetas.setLayout(jpMetasLayout);
        jpMetasLayout.setHorizontalGroup(
            jpMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMetasLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2))
        );
        jpMetasLayout.setVerticalGroup(
            jpMetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMetasLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cbmObstetras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmObstetras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Obstetra" }));
        cbmObstetras.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmObstetrasItemStateChanged(evt);
            }
        });
        cbmObstetras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmObstetrasActionPerformed(evt);
            }
        });

        lblObstetra.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblObstetra.setText("Obstetra:");

        btnMetas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMetas.setText("Metas Mensuales");
        btnMetas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMetas.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetasActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jCheckBox1.setText("Mostrar Metas Totales");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblObstetra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(cbmObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(btnMetas)
                        .addGap(47, 47, 47))
                    .addComponent(jpMetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObstetra, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbmObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMetas)
                    .addComponent(jCheckBox1))
                .addGap(12, 12, 12)
                .addComponent(jpMetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Metas Individuales", jPanel3);

        jtDetalleCitas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jtDetalleCitas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jtDetalleCitas);

        jdcInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Fecha de Inicio");

        jdcFin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Fecha Fin");

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1217, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10)
                        .addComponent(jdcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(jdcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnBuscar)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reporte Detalle Citas", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        btnExportar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExportar.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(btnExportar)
                .addGap(58, 58, 58))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnExportar)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetasActionPerformed
        if (btnMetas.getText().equals("Metas Anuales")) {
            cargarMetas();
        }else{
            cargarMetasMensuales();
        }
    }//GEN-LAST:event_btnMetasActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
                ExportarPDF();
                try {
                        ExportarExcel();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void cbmObstetrasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmObstetrasItemStateChanged
        if (cbmObstetras.getSelectedIndex()==0) {
            cargarMetasMensuales();
        } else {
            cargarMetasMensualesFiltradas();
        }
    }//GEN-LAST:event_cbmObstetrasItemStateChanged

    private void cbmObstetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmObstetrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmObstetrasActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jpMetas.setVisible(true);
        } else {
            jpMetas.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        capturarFechas();
        DetalleCitas();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnMetas;
    private javax.swing.JComboBox<String> cbmObstetras;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jdcFin;
    private com.toedter.calendar.JDateChooser jdcInicio;
    private javax.swing.JPanel jpMetas;
    private javax.swing.JTable jtDetalleCitas;
    private javax.swing.JTable jtMetas;
    private javax.swing.JTable jtMetasIndividuales;
    private javax.swing.JLabel lblObstetra;
    // End of variables declaration//GEN-END:variables

}
