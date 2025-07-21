package GUI;

import static GUI.frmRegistro.instancia;
import Model.Programa;
import Strategy.IGestorEntidad;
import Strategy.ModoFormulario;
import Util.Alerta;
import Util.Confirmacion;
import Util.Formato;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class frmRegistroPrograma extends JFrame implements Formato,Alerta,Confirmacion{
    
    private static frmRegistroPrograma instancia;
    public IGestorEntidad gestor;
    public ModoFormulario modo;
    
    public void CargarDatos(){
        Programa p = (Programa) gestor.cargarDatos(frmBase.id);
        
        txtNombre.setText(p.getNombre());
        txtDescripcion.setText(p.getDescripcion());
        txtMeta.setText(String.valueOf(p.getMeta()));
        ckbEstado.setSelectedItem(p.getEstado());
        jycAño.setValue(p.getPeriodo());
    }
      
    public void LimpiarCampos(){
        txtNombre.setText("");
        txtDescripcion.setText("");
        jycAño.setValue(2025);
        txtMeta.setText("");
        ckbEstado.setSelectedIndex(0);
        
        txtNombre.requestFocus();
    }
    
    private boolean validarCampos(){
        
        JTextField[] campos = {txtNombre, txtDescripcion, txtMeta};

        String[] mensajes = {
        "El Nombre no puede estar vacío!",
        "La Descripcion no puede estar vacía",
        "La Meta anual no puede estar vacía!",
        };

        for (int i = 0; i < campos.length; i++) {
            if(campos[i].getText().isEmpty()){
                Alerta(mensajes[i]);
                campos[i].requestFocus();
                return false;
            }
        }    
        return true;
    }
    
    private void formatearInicio(){
        FormatearYearChooser(jycAño);
        FormatearComboBox(ckbEstado);
        FormatoBtnCerrar(btnX,Color.gray);
    }
    
    public static void EliminarInstacia(){
        if (instancia != null){
            instancia.dispose();
            instancia = null;
        }
    }
    
    private void Registrar(){
        
        if (validarCampos()) {
            Programa p = new Programa(
                    0,
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    jycAño.getValue(),
                    Integer.parseInt(txtMeta.getText()),
                    null
            );
            
            gestor.Registrar(p);
            
            if (ConfirmacionContinuar()) {
                LimpiarCampos();
            } else {
                dispose();
            }
        }
    }
    
    private void Actualizar(){
        
        if (validarCampos()) {

           String estado = String.valueOf(ckbEstado.getSelectedItem());    

            Programa p = new Programa(
                    frmBase.id,
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    jycAño.getValue(),
                    Integer.parseInt(txtMeta.getText()),
                    estado
            );

            gestor.Actualizar(p);
            dispose();
        }
    }
    
    private void Accion(){
        if (lblTitulo.getText().contains("REGISTRAR")) {
            Registrar();
        } else {
            Actualizar();
        }
    }
    
    
    private frmRegistroPrograma(IGestorEntidad gestor, ModoFormulario modo) {
        this.gestor = gestor;
        this.modo = modo;
        initComponents();     
        formatearInicio();
    }
    
    public static frmRegistroPrograma getInstancia(IGestorEntidad gestor, ModoFormulario modo){
        if (instancia == null) {
            instancia = new frmRegistroPrograma(gestor, modo);
        }
        return instancia;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpBase = new javax.swing.JPanel();
        jpLeft = new javax.swing.JPanel();
        jpBottom = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        ckbEstado = new javax.swing.JComboBox<>();
        btnRegistro = new javax.swing.JButton();
        jpRigth = new javax.swing.JPanel();
        jpTop = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnX = new javax.swing.JButton();
        jpCentral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jycAño = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        txtMeta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jpBase.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jpBase.setPreferredSize(new java.awt.Dimension(450, 592));
        jpBase.setLayout(new java.awt.BorderLayout());

        jpLeft.setPreferredSize(new java.awt.Dimension(30, 0));

        javax.swing.GroupLayout jpLeftLayout = new javax.swing.GroupLayout(jpLeft);
        jpLeft.setLayout(jpLeftLayout);
        jpLeftLayout.setHorizontalGroup(
            jpLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpLeftLayout.setVerticalGroup(
            jpLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpBase.add(jpLeft, java.awt.BorderLayout.LINE_START);

        jpBottom.setPreferredSize(new java.awt.Dimension(0, 200));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblEstado.setText("Estado:");

        ckbEstado.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ckbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EN PROCESO", "COMPLETADO", "INCOMPLETO", "CANCELADO" }));
        ckbEstado.setPreferredSize(new java.awt.Dimension(84, 30));
        ckbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbEstadoActionPerformed(evt);
            }
        });

        btnRegistro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnRegistro.setText("Registrar");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpBottomLayout = new javax.swing.GroupLayout(jpBottom);
        jpBottom.setLayout(jpBottomLayout);
        jpBottomLayout.setHorizontalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBottomLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ckbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBottomLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jpBottomLayout.setVerticalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBottomLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ckbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jpBase.add(jpBottom, java.awt.BorderLayout.PAGE_END);

        jpRigth.setPreferredSize(new java.awt.Dimension(30, 0));

        javax.swing.GroupLayout jpRigthLayout = new javax.swing.GroupLayout(jpRigth);
        jpRigth.setLayout(jpRigthLayout);
        jpRigthLayout.setHorizontalGroup(
            jpRigthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpRigthLayout.setVerticalGroup(
            jpRigthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpBase.add(jpRigth, java.awt.BorderLayout.LINE_END);

        jpTop.setPreferredSize(new java.awt.Dimension(382, 150));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("REGISTRAR PROGRAMA");

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

        javax.swing.GroupLayout jpTopLayout = new javax.swing.GroupLayout(jpTop);
        jpTop.setLayout(jpTopLayout);
        jpTopLayout.setHorizontalGroup(
            jpTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpTopLayout.setVerticalGroup(
            jpTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTopLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30))
        );

        jpBase.add(jpTop, java.awt.BorderLayout.PAGE_START);

        jpCentral.setMinimumSize(new java.awt.Dimension(382, 400));
        jpCentral.setLayout(new java.awt.GridLayout(4, 1, 0, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Nombre: ");
        jpCentral.add(jLabel1);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setPreferredSize(new java.awt.Dimension(64, 30));
        jpCentral.add(txtNombre);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Descripción:");
        jpCentral.add(jLabel2);

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion.setPreferredSize(new java.awt.Dimension(64, 30));
        jpCentral.add(txtDescripcion);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Año:");
        jpCentral.add(jLabel3);

        jycAño.setFocusable(false);
        jycAño.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jycAño.setPreferredSize(new java.awt.Dimension(76, 30));
        jpCentral.add(jycAño);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Meta Anual:");
        jpCentral.add(jLabel4);

        txtMeta.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMeta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMeta.setPreferredSize(new java.awt.Dimension(64, 30));
        jpCentral.add(txtMeta);

        jpBase.add(jpCentral, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpBase, javax.swing.GroupLayout.PREFERRED_SIZE, 622, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        Accion();
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void ckbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbEstadoActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnRegistro;
    public static javax.swing.JButton btnX;
    public static javax.swing.JComboBox<String> ckbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jpBase;
    private javax.swing.JPanel jpBottom;
    private javax.swing.JPanel jpCentral;
    private javax.swing.JPanel jpLeft;
    private javax.swing.JPanel jpRigth;
    private javax.swing.JPanel jpTop;
    private com.toedter.calendar.JYearChooser jycAño;
    public static javax.swing.JLabel lblEstado;
    public static javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtMeta;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
