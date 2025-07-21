package GUI;

import Model.Obstetra;
import Model.Paciente;
import Model.Persona;
import Strategy.GestorPaciente;
import Util.Alerta;
import Util.Confirmacion;
import Util.Formato;
import Strategy.IGestorEntidad;
import Strategy.ModoFormulario;
import Util.Resultado;
import Strategy.TipoEntidad;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class frmRegistro extends JFrame implements Formato,Resultado,Confirmacion,Alerta{
    
    public static frmRegistro instancia;
    public IGestorEntidad gestor;
    public TipoEntidad tipo;
    public ModoFormulario modo;
  
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<String> obtenerConsejosRegional() {
        ArrayList<String> consejos = new ArrayList<>();
        consejos.add("PIURA");
        consejos.add("LA LIBERTAD");
        consejos.add("LIMA CALLAO");
        consejos.add("AREQUIPA");
        consejos.add("ICA");
        consejos.add("JUNIN");
        consejos.add("AYACUCHO");
        consejos.add("PUNO");
        consejos.add("ANCASH – HUARAZ");
        consejos.add("CUSCO");
        consejos.add("LAMBAYEQUE");
        consejos.add("TACNA");
        consejos.add("SAN MARTIN");
        consejos.add("CAJAMARCA");
        consejos.add("ANCASH – CHIMBOTE");
        consejos.add("LORETO");
        consejos.add("HUANUCO");
        consejos.add("TUMBES");
        consejos.add("UCAYALI");
        consejos.add("MOQUEGUA");
        consejos.add("AMAZONAS");
        consejos.add("HUANCAVELICA");
        consejos.add("APURIMAC");
        consejos.add("MADRE DE DIOS");
        consejos.add("PASCO");
        consejos.add("ANDAHUAYLAS");
        return consejos;
    }
      
    public void limpiarCampos(){
        
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDni.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        jdcFechaNac.setDate(null);
        
        txtNombres.requestFocus();
        
    }
    
    private boolean validarCampos(){
        
        JTextField[] campos = {txtNombres, txtApellidos, txtDni, txtDireccion, txtTelefono};
        
        if (!(jdcFechaNac.getDate() == null)) {

            String[] mensajes = {
            "El Nombre no puede estar vacío!",
            "Los Apellidos no pueden estar vacíos!",
            "El DNI no puede estar vacío!",
            "La Dirección no puede estar vacía!",
            "El Teléfono no puede estar vacío!",
            };

            for (int i = 0; i < campos.length; i++) {
                if(campos[i].getText().isEmpty()){
                    Alerta(mensajes[i]);
                    campos[i].requestFocus();
                    return false;
                }
            }
            
        } else {
            Alerta("Ingrese una Fecha de Nac. Válida!");
        }       
        return true;  
    }
    
    public void CargarDatosPaciente(){
        
        if (AlertaSeleccion(frmBase.id)) {
            
            Paciente p = (Paciente) gestor.cargarDatos(frmBase.id);

            try {          
                Date fecha = formatoFecha.parse(p.getFechaNac());

                txtNombres.setText(p.getNombres());
                txtApellidos.setText(p.getApellidos());
                txtDni.setText(p.getDni());
                jdcFechaNac.setDate(fecha);
                txtDireccion.setText(p.getDireccion());
                txtTelefono.setText(p.getTelefono());
                ckbEstado.setSelectedItem(p.getEstado());
                jsNroHijos.setValue(p.getNroHijos());

            } catch (ParseException ex) {
                System.out.println(ex);
            }
        } else {
            return;
        }   
    }
    
    public void CargarDatosObstetra(){
        
        if (AlertaSeleccion(frmBase.id)) {
            
            Obstetra o = (Obstetra) gestor.cargarDatos(frmBase.id);

            try {          
                Date fecha = formatoFecha.parse(o.getFechaNac());
                Date fechaCol = formatoFecha.parse(o.getFechaColegiatura());
                
                txtNombres.setText(o.getNombres());
                txtApellidos.setText(o.getApellidos());
                txtDni.setText(o.getDni());
                jdcFechaNac.setDate(fecha);
                txtDireccion.setText(o.getDireccion());
                txtTelefono.setText(o.getTelefono());
                ckbEstado.setSelectedItem(o.getEstado());
                txtcolegiatura.setText(o.getNroColegiatura());
                cmbCro.setSelectedItem(o.getColegio());
                jdcFechaColegiatura.setDate(fechaCol);

            } catch (ParseException ex) {
                System.out.println(ex);
            }
        } else {
            return;
        }   
    }
    
    private void RegistrarPaciente( ){

        if (validarCampos()) {
            
            String fechaNac = formatoFecha.format(jdcFechaNac.getDate());
            
            Paciente p = new Paciente(
                (int)jsNroHijos.getValue(),
                0,
                txtNombres.getText(), 
                txtApellidos.getText(), 
                txtDni.getText(), 
                fechaNac, 
                txtTelefono.getText(), 
                txtDireccion.getText(),
                null
            );
            
            gestor.Registrar(p);
            
            if (ConfirmacionContinuar()) {
                limpiarCampos();
            } else {
                dispose();
            }
            
        }
        
    }
       
    private void RegistrarObstetra( ){

        if (validarCampos()) {
            
            String fechaNac = formatoFecha.format(jdcFechaNac.getDate());
            String colegio = String.valueOf(cmbCro.getSelectedItem());
            String fechaCol = formatoFecha.format(jdcFechaColegiatura.getDate());
            Obstetra o = new Obstetra(
                txtcolegiatura.getText(),
                colegio,
                fechaCol,
                0,
                txtNombres.getText(), 
                txtApellidos.getText(), 
                txtDni.getText(), 
                fechaNac, 
                txtTelefono.getText(), 
                txtDireccion.getText(),
                null
            );
            
            gestor.Registrar(o);
            
            if (ConfirmacionContinuar()) {
                limpiarCampos();
            } else {
                dispose();
            }
            
        }
        
    }
    
    private void ActualizarPaciente(){
    
        String fechaNac = formatoFecha.format(jdcFechaNac.getDate());
        
        if (validarCampos()) {

            String estado = String.valueOf(ckbEstado.getSelectedItem());

            Paciente p = new Paciente(
                (int)jsNroHijos.getValue(),
                frmBase.id,
                txtNombres.getText(), 
                txtApellidos.getText(), 
                txtDni.getText(), 
                fechaNac, 
                txtTelefono.getText(), 
                txtDireccion.getText(),
                estado
            );

            gestor.Actualizar(p);

            dispose();
        }

    }
    
    private void ActualizarObstetra(){
    
        String fechaNac = formatoFecha.format(jdcFechaNac.getDate());
        String fechaCol = formatoFecha.format(jdcFechaColegiatura.getDate());
        
        if (validarCampos()) {

            String estado = String.valueOf(ckbEstado.getSelectedItem());
            String colegio = String.valueOf(cmbCro.getSelectedItem());

            Obstetra o = new Obstetra(
                txtcolegiatura.getText(),
                colegio,
                fechaCol,
                frmBase.id,
                txtNombres.getText(), 
                txtApellidos.getText(), 
                txtDni.getText(), 
                fechaNac, 
                txtTelefono.getText(), 
                txtDireccion.getText(),
                estado
            );

            gestor.Actualizar(o);

            dispose();
        }

    }
    

    
    private void FormatoInicio(){
        FormatearComboBox(ckbEstado);
        FormatearDateChooser(jdcFechaNac);
        FormatoBtnCerrar(btnX,Color.gray);
    }
  
    private TipoEntidad obtenerTipo(IGestorEntidad gestor) {
        if (gestor instanceof GestorPaciente) {
            return TipoEntidad.PACIENTE;
        } else {
            return TipoEntidad.OBSTETRA;
        } 
    }
    
    private void Accion(){
       
        if (lblTituloRegistro.getText().contains("REGISTRAR")) {
            if (gestor instanceof GestorPaciente) {
                RegistrarPaciente();
            } else {
                RegistrarObstetra();
            }
        } else {
        if (gestor instanceof GestorPaciente) {
                ActualizarPaciente();
            } else {
                ActualizarObstetra();
            }
        }   
    }
    
    public static void EliminarInstacia(){
        if (instancia != null){
            instancia.dispose();
            instancia = null;
        }
    }
    
    private void asignarComponentes() {
    if (gestor instanceof GestorPaciente) {

        lblNroHijos.setVisible(true);
        jsNroHijos.setVisible(true);
        jPanel1.remove(jpObstetras);

    } else {
        jpObstetras.setVisible(true);
        jPanel1.remove(jsNroHijos);
        jPanel1.remove(lblNroHijos);
    }

    jPanel1.revalidate(); 
    jPanel1.repaint();     

    this.pack();               
    this.setLocationRelativeTo(null); 
}


    

    private void llenarComboBoxConsejos() {
        ArrayList<String> consejos = obtenerConsejosRegional();
        cmbCro.removeAllItems();
        for (String consejo : consejos) {
            cmbCro.addItem(consejo);
        }
    }



    
    private frmRegistro(IGestorEntidad gestor, ModoFormulario modo){
        this.modo = modo;
        this.gestor = gestor;
        this.tipo = obtenerTipo(gestor);
        this.setUndecorated(true);
        initComponents();
        FormatoInicio();
        asignarComponentes();
        llenarComboBoxConsejos();
        limpiarCampos();
     
    };

    
    public static frmRegistro getInstancia(IGestorEntidad gestor, ModoFormulario modo){
        if (instancia == null) {
            instancia = new frmRegistro(gestor, modo);
        }
        return instancia;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTituloRegistro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jdcFechaNac = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        btnRegistro = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnX = new javax.swing.JButton();
        jpObstetras = new javax.swing.JPanel();
        lblCro = new javax.swing.JLabel();
        cmbCro = new javax.swing.JComboBox<>();
        lblColegiatura = new javax.swing.JLabel();
        txtcolegiatura = new javax.swing.JTextField();
        lblFechaColegiatura = new javax.swing.JLabel();
        jdcFechaColegiatura = new com.toedter.calendar.JDateChooser();
        lblNroHijos = new javax.swing.JLabel();
        ckbEstado = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        jsNroHijos = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));

        lblTituloRegistro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTituloRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloRegistro.setText("REGISTRO");

        jPanel2.setLayout(new java.awt.GridLayout(6, 2, 0, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Nombres:");
        jPanel2.add(jLabel2);

        txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtNombres);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Apellidos");
        jPanel2.add(jLabel3);

        txtApellidos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtApellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtApellidos);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("DNI:");
        jPanel2.add(jLabel4);

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        jPanel2.add(txtDni);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Fecha Nac. :");
        jPanel2.add(jLabel5);

        jdcFechaNac.setDateFormatString("dd/MM/yyyy");
        jdcFechaNac.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel2.add(jdcFechaNac);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Dirección:");
        jPanel2.add(jLabel6);

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtDireccion);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Telefono:");
        jPanel2.add(jLabel7);

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTelefono);

        btnRegistro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRegistro.setText("Registrar");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnX.setBorder(null);
        btnX.setDoubleBuffered(true);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnX, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnX, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jpObstetras.setLayout(new java.awt.GridLayout(3, 2, 0, 30));

        lblCro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCro.setText("Consejo Regional O.:");
        jpObstetras.add(lblCro);

        cmbCro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbCro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar CRO" }));
        jpObstetras.add(cmbCro);

        lblColegiatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblColegiatura.setText("N° Colegiatura:");
        jpObstetras.add(lblColegiatura);

        txtcolegiatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtcolegiatura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcolegiatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcolegiaturaKeyTyped(evt);
            }
        });
        jpObstetras.add(txtcolegiatura);

        lblFechaColegiatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFechaColegiatura.setText("Fecha de Colegiatura:");
        jpObstetras.add(lblFechaColegiatura);

        jdcFechaColegiatura.setDateFormatString("dd/MM/yyyy");
        jdcFechaColegiatura.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jpObstetras.add(jdcFechaColegiatura);

        lblNroHijos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNroHijos.setText("Nro de Hijos:");

        ckbEstado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ckbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEstado.setText("Estado:");

        jsNroHijos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTituloRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpObstetras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNroHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ckbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jsNroHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTituloRegistro)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpObstetras, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNroHijos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsNroHijos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ckbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        FormatoDni(txtDni, evt);
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        FormatoTelefono(txtTelefono, evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        Accion();
    }//GEN-LAST:event_btnRegistroActionPerformed

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

    private void ckbEstado1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbEstado1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbEstado1ActionPerformed

    private void txtcolegiaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcolegiaturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcolegiaturaKeyTyped

    public static void main(String args[]) {
        
        try {
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistro;
    public static javax.swing.JButton btnX;
    public static javax.swing.JComboBox<String> ckbEstado;
    private javax.swing.JComboBox<String> cmbCro;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private com.toedter.calendar.JDateChooser jdcFechaColegiatura;
    private com.toedter.calendar.JDateChooser jdcFechaNac;
    private javax.swing.JPanel jpObstetras;
    private javax.swing.JSpinner jsNroHijos;
    private javax.swing.JLabel lblColegiatura;
    private javax.swing.JLabel lblCro;
    public static javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaColegiatura;
    public static javax.swing.JLabel lblNroHijos;
    public javax.swing.JLabel lblTituloRegistro;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtcolegiatura;
    // End of variables declaration//GEN-END:variables
}
