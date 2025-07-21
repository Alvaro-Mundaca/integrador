package GUI;

import DAO.CitaDAO;
import DAO.ObstetraDAO;
import DAO.PacienteDAO;
import DAO.ProgramaDAO;
import Model.Cita;
import Strategy.IGestorEntidad;
import Strategy.ModoFormulario;
import Util.Alerta;
import Util.CitaHelper;
import Util.Confirmacion;
import Util.Formato;
import Util.HorarioHelper;
import Util.Resultado;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


public class frmRegistroCitas extends JFrame implements Formato,HorarioHelper,Resultado,Alerta,Confirmacion,CitaHelper{

    
    public static int id;
    private static frmRegistroCitas instancia;
    public IGestorEntidad gestor;
    public ModoFormulario modo;
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String horaSeleccionada;
 
    LocalDate hoy = LocalDate.now();
    LocalTime hora;
    
    private boolean validarCampos(){

        JComboBox[] campos = {cbmPacientes, cbmObstetras, cbmProgramas};
        
        if (!(horaSeleccionada == null)) {
            if (!(txtDescripcion.getText().equals(""))) {

                String[] mensajes = {
                "Por favor seleccione un Paciente!",
                "Por favor seleccione una Obstetra!",
                "Por favor seleccione un Programa!",
                };

                for (int i = 0; i < campos.length; i++) {
                    if(campos[i].getSelectedIndex() == 0){
                        Alerta(mensajes[i]);
                        campos[i].requestFocus();
                        return false;
                    }
                }

            } else {
                Alerta("Por favor ingrese una Descripción!");
                return false;
            }  
        } else {
            Alerta("Por favor seleccione una Hora!");
            return false;
        }
        return true;     
        
    }
    
    public boolean validarFecha() {
        if (jdcFechaDiario.getDate() == null) {
            Alerta("Debe seleccionar una fecha.");
            return false;
        }

        LocalDate fechaSeleccionada = jdcFechaDiario.getDate()
            .toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();

        if (fechaSeleccionada.isBefore(LocalDate.now())) {
            Alerta("La fecha seleccionada no puede ser anterior al día de hoy.");
            jdcFechaDiario.setDate(java.sql.Date.valueOf(hoy)); // Restaurar fecha actual
            return false;
        } else {
            return true;
        }
}
    
    private void limpiarCampos(){
        txtDescripcion.setText("");
        cbmObstetras.setSelectedIndex(0);
        cbmPacientes.setSelectedIndex(0);
        cbmProgramas.setSelectedIndex(0);
        jdcFechaDiario.setDate(java.sql.Date.valueOf(hoy));
        cbmTurnoDiario.setSelectedIndex(0);
    }
    
    private void CargarDatosComboBox(){
        PacienteDAO pacienteDao = new PacienteDAO();
        ProgramaDAO programaDao = new  ProgramaDAO();
        ObstetraDAO obstetraDao = new ObstetraDAO();
        
        pacienteDao.ObtenerPacientes(cbmPacientes);
        programaDao.ObtenerProgramas(cbmProgramas);
        obstetraDao.ObtenerObstetrasComboBox(cbmObstetras);
        
        AutoCompleteDecorator.decorate(cbmPacientes); 
        AutoCompleteDecorator.decorate(cbmProgramas); 
        AutoCompleteDecorator.decorate(cbmObstetras); 
    }
   
    private void ObtenerHoraTablaDiario(){
        int fila = jtDiario.getSelectedRow();
        
        if (fila < 0) {
            return;  // No hay selección válida, salimos
        }
        
        Object nuevaHora = jtDiario.getValueAt(fila, 0);
        
        if(nuevaHora != null){
           horaSeleccionada = nuevaHora.toString();
         
        } else {
            System.out.println("celda vacia!");
        }
        
    } 
    
    private void Registrar(){
        
        if (validarCampos()) {
            String obstetra = String.valueOf(cbmObstetras.getSelectedItem());
            String programa = String.valueOf(cbmProgramas.getSelectedItem());
            String paciente = String.valueOf(cbmPacientes.getSelectedItem());
            String descripcion = txtDescripcion.getText();
            String fechaCita = sdf.format(jdcFechaDiario.getDate())+ " " + horaSeleccionada;
            

            Cita c = new Cita(0, programa, paciente, obstetra, null, fechaCita,descripcion, null);   

            if(ConfirmarRegistroCita(c.toString())){
                CitaDAO dao = new CitaDAO();
                dao.Registrar(c);
                ResultadoRegistro(dao.resultado);
                frmCitas.getInstancia(gestor).mostrarResumenCalendario();
            }
            if (ConfirmacionContinuar()) {
                limpiarCampos();
            } else {
                dispose();
            }
            
        }
 
    }
    
    private void Actualizar(){
        if (AlertaSeleccion(id)) {
            if (ConfirmacionActualizar()) {
//                mostrarFormularioRegistro(gestor, ACTUALIZACION);  
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
           System.out.println(fecha);
           return fecha;
           
        }
         return LocalDate.now();        
    }
        
    private void ConfigurarTablaDiario(){
        
        DefaultTableModel d = dtm(AsignarFechaEncabezadoDiario(ObtenerFechaDiario(jdcFechaDiario)));
        
        horaSeleccionada = null;
        
        if(cbmTurnoDiario.getSelectedIndex()==0){
            for (int i = 8; i <= 13; i++) {
            hora = LocalTime.of(i, 0);
            d.addRow(new Object[]{hora.toString()});
            
            }
        } else {
            for (int i = 14; i <= 19; i++) {
            hora = LocalTime.of(i, 0);
            d.addRow(new Object[]{hora.toString()});
            
            }
        }
        
        jtDiario.setModel(d);
        FormatearTablaHorarios(jtDiario);
        jtDiario.setRowHeight(59);
        
    }
    
    
    public static void restablecerId(){
        id = 0;
    }
  
//    private void configurarGestor(){
//        btnRegistrar.requestFocus();
//        FormatearBotonesCRUD(btnEliminar,btnRegistrar,btnEditar,btnExportar);
//        
//        if(gestor instanceof GestorObstetra){
//            lblTitulo.setText("Gestión de Obstetras");
//        } else if(gestor instanceof GestorPaciente){
//            lblTitulo.setText("Gestión de Pacientes");
//        } else {
//            lblTitulo.setText("Gestión de Programas");
//        }
//    }
    
    private void configurarListeners() { 
        
        jtDiario.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                ObtenerHoraTablaDiario();
            }});
    }

    
    private void configurarInicio(){
        configurarListeners();
        jdcFechaDiario.setDate(java.sql.Date.valueOf(hoy));
        FormatoBtnCerrar(btnX,Color.gray);
        ConfigurarTablaDiario();
//        configurarGestor();
        CargarDatosComboBox();
//        System.out.println(gestor);
        lblEstado.setVisible(false);
        cbmEstado.setVisible(false);
    }
   
   public static void EliminarInstacia(){
        if (instancia != null){
            instancia.dispose();
            instancia = null;
        }
    }
    
//    public frmRegistroCitas() {
//        initComponents();     
//        configurarInicio();
//    }

   private frmRegistroCitas(IGestorEntidad gestor, ModoFormulario modo) {
        this.gestor = gestor;
        this.modo = modo;
        initComponents();     
        configurarInicio();
    }
   
   public static frmRegistroCitas getInstancia(IGestorEntidad gestor, ModoFormulario modo){
        if (instancia == null) {
            instancia = new frmRegistroCitas(gestor, modo);
        }
        return instancia;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbmTurnoDiario = new javax.swing.JComboBox<>();
        jdcFechaDiario = new com.toedter.calendar.JDateChooser();
        btnRegistro = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbmObstetras = new javax.swing.JComboBox<>();
        cbmPacientes = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtDiario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnX = new javax.swing.JButton();
        cbmProgramas = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbmEstado = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 5, true));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Seleccionar fecha:  ");

        cbmTurnoDiario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmTurnoDiario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mañana", "Tarde" }));
        cbmTurnoDiario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmTurnoDiarioItemStateChanged(evt);
            }
        });
        cbmTurnoDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmTurnoDiarioActionPerformed(evt);
            }
        });

        jdcFechaDiario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jdcFechaDiario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcFechaDiarioPropertyChange(evt);
            }
        });

        btnRegistro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRegistro.setText("Agendar Cita");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Paciente:");

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

        cbmPacientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmPacientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Paciente" }));
        cbmPacientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmPacientesItemStateChanged(evt);
            }
        });
        cbmPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmPacientesActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Obstetra:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Programa:");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Atención");

        jtDiario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtDiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "HORA", "ESTADO"
            }
        ));
        jtDiario.setCellSelectionEnabled(true);
        jtDiario.setDoubleBuffered(true);
        jScrollPane4.setViewportView(jtDiario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnX.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXMouseExited(evt);
            }
        });
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cbmProgramas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmProgramas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Programa" }));
        cbmProgramas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmProgramasItemStateChanged(evt);
            }
        });
        cbmProgramas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmProgramasActionPerformed(evt);
            }
        });

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEstado.setText("Estado:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Turno:");

        cbmEstado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbmEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Programa" }));
        cbmEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmEstadoItemStateChanged(evt);
            }
        });
        cbmEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmEstadoActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 18)), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1))); // NOI18N
        txtDescripcion.setDoubleBuffered(true);
        jScrollPane5.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(5, 5, 5)
                        .addComponent(jdcFechaDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(cbmTurnoDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(356, 356, 356))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbmEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbmProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbmPacientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbmObstetras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(40, 40, 40))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160))))))
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFechaDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmTurnoDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbmTurnoDiarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmTurnoDiarioItemStateChanged
        ConfigurarTablaDiario();
    }//GEN-LAST:event_cbmTurnoDiarioItemStateChanged

    private void cbmTurnoDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmTurnoDiarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmTurnoDiarioActionPerformed

    private void jdcFechaDiarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcFechaDiarioPropertyChange
        ConfigurarTablaDiario();
    }//GEN-LAST:event_jdcFechaDiarioPropertyChange

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        if (frmHome.jcbTesting.isSelected()) {
            Registrar();
        } else if(validarFecha()){
            Registrar();
        }
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void cbmObstetrasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmObstetrasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmObstetrasItemStateChanged

    private void cbmObstetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmObstetrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmObstetrasActionPerformed

    private void cbmPacientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmPacientesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmPacientesItemStateChanged

    private void cbmPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmPacientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmPacientesActionPerformed

    private void btnXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXMouseEntered
        FormatoBtnCerrar(btnX,Color.white);
    }//GEN-LAST:event_btnXMouseEntered

    private void btnXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXMouseExited
        FormatoBtnCerrar(btnX,Color.gray);
    }//GEN-LAST:event_btnXMouseExited

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        frmBase.restablecerId();
        dispose();
    }//GEN-LAST:event_btnXActionPerformed

    private void cbmProgramasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmProgramasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmProgramasItemStateChanged

    private void cbmProgramasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmProgramasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmProgramasActionPerformed

    private void cbmEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmEstadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmEstadoItemStateChanged

    private void cbmEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmEstadoActionPerformed

    public static void main(String args[]) {
        
        try {
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 18));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 16));
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
//            frmRegistroCitas f = new frmRegistroCitas();
//            f.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnRegistro;
    private javax.swing.JButton btnX;
    public static javax.swing.JComboBox<String> cbmEstado;
    private javax.swing.JComboBox<String> cbmObstetras;
    private javax.swing.JComboBox<String> cbmPacientes;
    private javax.swing.JComboBox<String> cbmProgramas;
    private javax.swing.JComboBox<String> cbmTurnoDiario;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jdcFechaDiario;
    private javax.swing.JTable jtDiario;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDescripcion;
    // End of variables declaration//GEN-END:variables

}
