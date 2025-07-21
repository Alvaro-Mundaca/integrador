package GUI;

import static DAO.LoginDAO.*;
import DAO.CitaDAO;
import DAO.ObstetraDAO;
import Strategy.*;
import Util.*;
import static Strategy.ModoFormulario.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.*;

public class frmCitas extends JInternalFrame implements Formato,Resultado,Confirmacion,Alerta,CitaImpl,FrmRegistroHelper,HorarioHelper{

    public static int id;
    private static frmCitas instancia;
    public IGestorEntidad gestor;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ExportHelper export = new ExportHelper();
    
    private final String[] cabeceras = {"LUNES","MARTES","MIÉRCOLES","JUEVES","VIERNES","SÁBADO","DOMINGO"};
    private final String[] meses = {"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
    private final String[] cabeceraCitasCalendarioPorObstetra = {"ID","HORA","PACIENTE","PROGRAMA","ESTADO"};
    private final String[] cabeceraCitasCalendario = {"ID","HORA","OBSTETRA","PACIENTE","PROGRAMA","ESTADO"};
    
    private CitaDAO dao = new CitaDAO();
    
    public static int año, mes;
    Object[] dia = new Object[7];   
    LocalDate hoy = LocalDate.now();
    LocalTime hora;
    LocalDate fechaSeleccionada = LocalDate.now();
    boolean box = false;
    
    
    // Metodos Calendario
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
    
    private void estilizarYearChooser() {
        FormatearYearChooser(jycAño);
        
        Color fondo = new Color(255, 204, 0);
        Color texto = Color.BLACK;

        jycAño.setBackground(fondo);
        jycAño.setForeground(texto);
        jycAño.setFont(new Font("Segoe UI", 1, 18));

        JSpinner spinner = (JSpinner) jycAño.getSpinner();
        spinner.setBackground(fondo);
        spinner.setForeground(texto);
        spinner.setBorder(null);

        for (Component c : spinner.getComponents()) {
            if (c instanceof JButton boton) {
                boton.setBackground(fondo);
                boton.setForeground(texto);
                boton.setFocusPainted(false);
                boton.setBorder(null);
                boton.setOpaque(true); // Asegura que se pinte el fondo
            } else if (c instanceof JTextField textField) {
                textField.setBackground(fondo);
                textField.setForeground(texto);
                textField.setEditable(false); // Evita edición manual
                textField.setFocusable(false);
                textField.setBorder(null);
            }
        }
    }
    
    private void cargarComboMes(){
        for (String mes : meses) {
            boxMes.addItem(mes);
        }
    }
    
    private void actualizarBoxes(){
        box = true;
        boxMes.setSelectedIndex(mes-1);
        jycAño.setValue(año);
        box = false;
    }
    
    public void configurarCalendario() {
        asignarFecha();
        Feriados.cargarFeriados(año);
        
        jtCalendario.setDefaultRenderer(Object.class, new RenderDia());
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
        jtCalendario.setModel(dtm);
        jtCalendario.clearSelection();
        jtCalendario.setRowHeight(85);
        
        JTableHeader header = jtCalendario.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        header.setReorderingAllowed(false);
        
        if (año == hoy.getYear() && mes == hoy.getMonthValue()) {
            int diaActual = hoy.getDayOfMonth();

            for (int fila = 0; fila < jtCalendario.getRowCount(); fila++) {
                for (int col = 0; col < jtCalendario.getColumnCount(); col++) {
                    Object valor = jtCalendario.getValueAt(fila, col);
                    if (valor != null && valor instanceof Integer && (Integer) valor == diaActual) {
                        jtCalendario.changeSelection(fila, col, false, false);
                        return;
                    }
                }
            }
        }

    }
    
    public void mostrarResumenCalendario(){
        String obstetraNombre = boxObstetras.getSelectedItem().toString();
        CalendarioCitas.cargarCitasAgrupadas(obstetraNombre);
        configurarCalendario();
    }
    
    private void ObtenerFechaCalendario(){
        int fila = jtCalendario.getSelectedRow();
        int columna = jtCalendario.getSelectedColumn();
        
        if (fila < 0 || columna < 0) {
            return;  // No hay selección válida, salimos
        }
        
        Object diaSeleccionado = jtCalendario.getValueAt(fila, columna);
        
        if(diaSeleccionado != null){
            int dia = Integer.parseInt(diaSeleccionado.toString());
            fechaSeleccionada = LocalDate.of(año, mes, dia);
            tituloTablaCitas();
            System.out.println(fechaSeleccionada);
        } else {
            System.out.println("celda vacia!");
        }
    }
    

    // Metodos tabla citas
    private void tituloTablaCitas(){
        lblTituloDetalle.setText("Detalle Citas " + fechaSeleccionada);
    }

    private void cargarCitasCalendario(){
        DefaultTableModel d = dtm(cabeceraCitasCalendario);
        dao.ListarCitasCalendario(d,fechaSeleccionada.toString());  
        jtCitas.setModel(d);
        FormatoTablaCitasCalendario(jtCitas);
    }
    
    private void cargarCitasCalendarioPorObstetra(String obstetra){
        DefaultTableModel d = dtm(cabeceraCitasCalendarioPorObstetra);
        dao.ListarCitasCalendarioPorObstetra(d,fechaSeleccionada.toString(),obstetra);  
        jtCitas.setModel(d);
        FormatoTablaCitasCalendarioPorObstetra(jtCitas);
    }
    
    
    // Metodos Exportar
    private void ExportarPDF(){
        export.generarPDF_Custom(jtCitas, "Detalle Citas");
    }
    
    public void ExportarExcel() throws IOException{
        export.generarExcel_Custom(jtCitas, "Detalle Citas");
    }
    
    
    // Metodos Funcionales 
    private void configurarListeners() { 
        jycAño.addPropertyChangeListener("year", evt -> {
            // evt.getNewValue() viene como Integer con el nuevo año
            año = (Integer) evt.getNewValue();
            configurarCalendario();
        });
        
        jtCalendario.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObtenerFechaCalendario();
                
                if (boxObstetras.isVisible()) {
                    if (boxObstetras.getSelectedIndex() == 0) {
                        cargarCitasCalendario();
                    } else {
                        cargarCitasCalendarioPorObstetra(String.valueOf(boxObstetras.getSelectedItem()));
                    }
                } else {
                    cargarCitasCalendarioPorObstetra(String.valueOf(user));
                }
            }});

        // Listener para cambios de selección de columna
        jtCalendario.getColumnModel().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObtenerFechaCalendario();
                if (boxObstetras.isVisible()) {
                    if (boxObstetras.getSelectedIndex() == 0) {
                        cargarCitasCalendario();
                    } else {
                        cargarCitasCalendarioPorObstetra(String.valueOf(boxObstetras.getSelectedItem()));
                    }
                } else {
                    cargarCitasCalendarioPorObstetra(String.valueOf(user));
                }
            }});
    }
    
    private void asignarFecha(){
        mes = boxMes.getSelectedIndex()+1;
        año = jycAño.getValue();
    }
    
    public static void restablecerId(){
        id = 0;
    }

    private void Registrar(){
        mostrarFormularioRegistro(gestor, REGISTRO);
    }
    
    private void Actualizar(){
        if (AlertaSeleccion(id)) {
            if (ConfirmacionActualizar()) {
                mostrarFormularioRegistro(gestor, ACTUALIZACION);  
            }
        }
    }
    
    private void Eliminar(){
        if (AlertaSeleccion(id)){
            gestor.Eliminar(id);
        }
    }
    
    private LocalDate ObtenerFechaDiario(JDateChooser dateChooser){     
        if (dateChooser.getDate() != null) {
           LocalDate fecha = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           return fecha;
           
        }
         return LocalDate.now();        
    }
    
    private void cargarObstetras(){
        ObstetraDAO dao = new ObstetraDAO();
        dao.ObtenerObstetrasComboBox(boxObstetras);
    }
    
    private void controlPermisos(){
        if (tipo.equals("USER")) {
            boxObstetras.setVisible(false);
            boxObstetras.setSelectedItem(user);
            lblObstetras.setVisible(false);
            
            cargarCitasCalendarioPorObstetra(user);
            CalendarioCitas.cargarCitasAgrupadas(user);
        }else {
            boxObstetras.setVisible(true);
            boxObstetras.setSelectedIndex(0);
            lblObstetras.setVisible(true);
            cargarCitasCalendario();
            CalendarioCitas.cargarCitasAgrupadas(null);
        }
    }
    
    private void configurarInicio(){
        FormatearBotonesCRUD(btnEliminar,btnRegistrar,btnEditar,btnExportar);
        cargarObstetras();        
        jtCalendario.repaint();
        estilizarYearChooser();
        FormatearBotonesFlecha(btnAdelante, btnAtras);
        FormatearComboBox(boxMes);
        cargarComboMes();
        configurarListeners();
        mes = hoy.getMonthValue();
        año = hoy.getYear();
        actualizarBoxes();
        configurarCalendario();
        System.out.println(gestor);
        tituloTablaCitas();
        btnRegistrar.requestFocus();
   
    }
    
    public static void EliminarInstacia(){
        if (instancia != null){
            instancia.dispose();
            instancia = null;
        }
    }
    
    
    //Constructor
    private frmCitas(IGestorEntidad gestor) {
        this.gestor = gestor;
        initComponents();
        controlPermisos();
        configurarInicio();  
    }
    
    public static frmCitas getInstancia(IGestorEntidad gestor){
        if (instancia == null) {
            instancia = new frmCitas(gestor);
        }
        return instancia;
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jpCRUD = new javax.swing.JPanel();
        jpBotones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jpBusqueda = new javax.swing.JPanel();
        lblObstetras = new javax.swing.JLabel();
        boxObstetras = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jpMes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCalendario = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        boxMes = new javax.swing.JComboBox<>();
        jycAño = new com.toedter.calendar.JYearChooser();
        btnAdelante = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTituloDetalle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCitas = new javax.swing.JTable();

        setDoubleBuffered(true);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gestión de Citas");
        lblTitulo.setDoubleBuffered(true);

        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRegistrar.setText("Agregar");
        btnRegistrar.setDoubleBuffered(true);
        btnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRegistrar.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setDoubleBuffered(true);
        btnEditar.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setDoubleBuffered(true);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnExportar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnExportar.setText("Exportar");
        btnExportar.setDoubleBuffered(true);
        btnExportar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExportar.setMargin(new java.awt.Insets(3, 5, 3, 8));
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBotonesLayout = new javax.swing.GroupLayout(jpBotones);
        jpBotones.setLayout(jpBotonesLayout);
        jpBotonesLayout.setHorizontalGroup(
            jpBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotonesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnRegistrar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportar)
                .addGap(30, 30, 30))
        );
        jpBotonesLayout.setVerticalGroup(
            jpBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotonesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jpBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar)
                    .addComponent(btnExportar))
                .addGap(8, 8, 8))
        );

        lblObstetras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblObstetras.setText("Buscar Obstetra:");

        boxObstetras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        boxObstetras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Obstetra" }));
        boxObstetras.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxObstetrasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpBusquedaLayout = new javax.swing.GroupLayout(jpBusqueda);
        jpBusqueda.setLayout(jpBusquedaLayout);
        jpBusquedaLayout.setHorizontalGroup(
            jpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblObstetras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boxObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jpBusquedaLayout.setVerticalGroup(
            jpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObstetras)
                    .addComponent(boxObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpCRUDLayout = new javax.swing.GroupLayout(jpCRUD);
        jpCRUD.setLayout(jpCRUDLayout);
        jpCRUDLayout.setHorizontalGroup(
            jpCRUDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCRUDLayout.createSequentialGroup()
                .addComponent(jpBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(jpBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpCRUDLayout.setVerticalGroup(
            jpCRUDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jpBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setMaximumSize(new java.awt.Dimension(1117, 620));
        jPanel5.setPreferredSize(new java.awt.Dimension(1117, 620));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setMaximumSize(new java.awt.Dimension(30, 512));
        jPanel6.setMinimumSize(new java.awt.Dimension(30, 512));
        jPanel6.setPreferredSize(new java.awt.Dimension(30, 512));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel7.setMaximumSize(new java.awt.Dimension(30, 512));
        jPanel7.setMinimumSize(new java.awt.Dimension(30, 512));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, java.awt.BorderLayout.WEST);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel3.setText(" ");
        jPanel5.add(jLabel3, java.awt.BorderLayout.PAGE_END);

        jtCalendario.setModel(new javax.swing.table.DefaultTableModel(
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
        jtCalendario.setColumnSelectionAllowed(true);
        jtCalendario.setFocusable(false);
        jtCalendario.setSelectionBackground(new java.awt.Color(64, 164, 255));
        jtCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCalendarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCalendario);

        jPanel8.setBackground(new java.awt.Color(255, 204, 0));

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(boxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jycAño, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(btnAdelante, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jycAño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdelante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(boxMes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btnAtras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblTituloDetalle.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblTituloDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloDetalle.setText("Detalle Citas");

        jtCitas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtCitas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloDetalle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout jpMesLayout = new javax.swing.GroupLayout(jpMes);
        jpMes.setLayout(jpMesLayout);
        jpMesLayout.setHorizontalGroup(
            jpMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpMesLayout.setVerticalGroup(
            jpMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jpMesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpMesLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel5.add(jpMes, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpCRUD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(32, 32, 32)
                .addComponent(jpCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        Registrar();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Actualizar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
//        Eliminar();
        OpcionesDesplegables.animarOcultar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
    ExportarPDF();
        try {
                ExportarExcel();
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void boxObstetrasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxObstetrasItemStateChanged
        mostrarResumenCalendario();
    }//GEN-LAST:event_boxObstetrasItemStateChanged

    private void jtCalendarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCalendarioMouseClicked

    }//GEN-LAST:event_jtCalendarioMouseClicked

    private void boxMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxMesItemStateChanged
        configurarCalendario();
    }//GEN-LAST:event_boxMesItemStateChanged

    private void jycAñoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jycAñoInputMethodTextChanged

    }//GEN-LAST:event_jycAñoInputMethodTextChanged

    private void jycAñoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jycAñoPropertyChange

    }//GEN-LAST:event_jycAñoPropertyChange

    private void btnAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdelanteActionPerformed
        AvanzarMes();
    }//GEN-LAST:event_btnAdelanteActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        AtrasMes();
    }//GEN-LAST:event_btnAtrasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxMes;
    private javax.swing.JComboBox<String> boxObstetras;
    private javax.swing.JButton btnAdelante;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpBotones;
    private javax.swing.JPanel jpBusqueda;
    private javax.swing.JPanel jpCRUD;
    private javax.swing.JPanel jpMes;
    private javax.swing.JTable jtCalendario;
    private javax.swing.JTable jtCitas;
    private com.toedter.calendar.JYearChooser jycAño;
    private javax.swing.JLabel lblObstetras;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloDetalle;
    // End of variables declaration//GEN-END:variables
}
