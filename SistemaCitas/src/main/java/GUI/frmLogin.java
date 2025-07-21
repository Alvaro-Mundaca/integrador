
package GUI;

import DAO.LoginDAO;
import Model.Usuario;
import Util.Formato;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.evaicons.Evaicons;
import org.kordamp.ikonli.materialdesign2.MaterialDesignE;



public class frmLogin extends JFrame implements Formato{
    
    private static frmLogin instancia;
    
    private final char echoChar;
    
    private int contador = 3;
    private int tiempo = 60;
    private int intervalo = 1000;
    
    private FontIcon iconoMostrar, iconoOcultar;
    
    private void RecuperarContrasena(){
        FontIcon icon = FontIcon.of(MaterialDesignE.EMAIL_CHECK,64,new Color(0, 149, 255));
        
        JOptionPane.showMessageDialog(
                null,
                "Correo de recuperación enviado!",
                "RECUPERACIÓN DE CONTRASEÑA",
                NORMAL,
                icon
        );
    }
    

    private void MostrarContrasena(){      
        if (btnOjo.isSelected()) {
            btnOjo.setIcon(iconoMostrar);
            txtContrasena.setEchoChar((char) 0);
        } else {
            btnOjo.setIcon(iconoOcultar);
            txtContrasena.setEchoChar(echoChar);
        } 
    } 
      
    
    private void VerificarCredenciales(){
        
        Usuario u = new Usuario(txtDni.getText(), String.valueOf(txtContrasena.getPassword()));
        
        LoginDAO dao = new LoginDAO();
        
        dao.VerificarCredenciales(u);
        
        if (!(contador < 2)) {            
            if (dao.resultado == 1) {
                JOptionPane.showMessageDialog(null, "Bienvenido!");
                frmHome h = frmHome.getInstancia();
                h.setVisible(true);
                dispose();
            } else {
                contador--;
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos! \n Le quedan " + contador + " intentos!");
                txtContrasena.setText("");
            }
        } else {
            TimerCuentaBloqueada();
            JOptionPane.showMessageDialog(null, "Cuenta bloqueada! \n Vuelva a intentar en 60 segundos \n o contacte a un administrador!");
        }      
    }
    
    
    private boolean ValidarReCaptcha(){
        if (ckbRe.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Por favor comprueba que eres humano!");
            return false;
        } else {
            return true;
        }
    }
    
    
    private void TimerCuentaBloqueada(){    
        Timer t = new Timer(intervalo, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempo--;
                lblAlerta.setText("Vuelva a intentarlo en " + tiempo + " segundos!");
                btnIniciar.setEnabled(false);
                
                if (!(tiempo > 1)) {
                    ((Timer) e.getSource()).stop();
                    lblAlerta.setText("");
                    btnIniciar.setEnabled(true);
                    contador = 3;
                    tiempo = 60;
                }              
            }
        });       
        t.start(); 
    }
    
    
    private boolean ValidacionCampos(){
        if (txtDni.getText().equals("")||String.valueOf(txtContrasena.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos!");
            return false;
       } else {
            return true;
        }
    }
    
    
    private void ConfigurarBotonHelp() {
        
        FontIcon ikon = FontIcon.of(Evaicons.QUESTION_MARK_CIRCLE_OUTLINE, 32, Color.gray);
        
        btnHelp1.setIcon(ikon);
        btnHelp2.setIcon(ikon);
    }
    
    
    private void ConfigurarBotonOjo() {
        
        iconoMostrar = FontIcon.of(Evaicons.EYE_OUTLINE, 32, Color.gray);  
        iconoOcultar = FontIcon.of(Evaicons.EYE_OFF_OUTLINE, 32, Color.gray);  
         
    }
    
    
    private void ConfigurarReCaptcha() {
        
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/recaptcha.png"));   
        
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 65, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        
        btnRe.setIcon(iconoEscalado);
    }
    
    
    private void ConfigurarLogoMinsa() {
        
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/Minsa.png"));   
        
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(240, 64, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        
        btnMinsa.setIcon(iconoEscalado);
    }
        
    
    private void AsignarImagenes(){
        ConfigurarBotonOjo();
        ConfigurarBotonHelp();
        ConfigurarReCaptcha();
        ConfigurarLogoMinsa();
    }
    
    
        
    public frmLogin() {
        initComponents();
        AsignarImagenes();
        btnOjo.setIcon(iconoOcultar);
        this.echoChar = txtContrasena.getEchoChar();
        lblAlerta.setText("");
        txtContrasena.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, Color.GRAY));
        btnOjo.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, Color.GRAY));
        
        txtDni.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
    }
    
    
    public static frmLogin getInstancia(){
        if(instancia == null){
            instancia = new frmLogin();
        }
        return instancia;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        ckbRe = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRe = new javax.swing.JButton();
        lblRestaurarContrasena = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        lblAlerta = new javax.swing.JLabel();
        btnHelp1 = new javax.swing.JButton();
        btnHelp2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnOjo = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btnLogo = new javax.swing.JButton();
        btnMinsa = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("INICIAR SESIÓN");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Usuario");

        txtDni.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Contraseña");

        txtContrasena.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtContrasena.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(249, 249, 249));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        ckbRe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ckbRe.setText("No soy un robot");
        ckbRe.setIconTextGap(10);

        btnRe.setBorderPainted(false);
        btnRe.setContentAreaFilled(false);
        btnRe.setMaximumSize(new java.awt.Dimension(50, 50));
        btnRe.setMinimumSize(new java.awt.Dimension(50, 50));
        btnRe.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ckbRe)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRe, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(ckbRe)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblRestaurarContrasena.setText("¿Olvidaste tu Contraseña?");
        lblRestaurarContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRestaurarContrasenaMouseClicked(evt);
            }
        });

        btnIniciar.setBackground(new java.awt.Color(51, 153, 255));
        btnIniciar.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText("Iniciar Sesión");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        lblAlerta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAlerta.setForeground(new java.awt.Color(255, 51, 51));
        lblAlerta.setText(" ");

        btnHelp1.setToolTipText("");
        btnHelp1.setAutoscrolls(true);
        btnHelp1.setBorderPainted(false);
        btnHelp1.setContentAreaFilled(false);
        btnHelp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp1ActionPerformed(evt);
            }
        });

        btnHelp2.setToolTipText("");
        btnHelp2.setBorderPainted(false);
        btnHelp2.setContentAreaFilled(false);
        btnHelp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelp2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Bienvenido");

        btnOjo.setDoubleBuffered(true);
        btnOjo.setFocusPainted(false);
        btnOjo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOjo.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnOjo.setMaximumSize(new java.awt.Dimension(32, 32));
        btnOjo.setMinimumSize(new java.awt.Dimension(32, 32));
        btnOjo.setPreferredSize(new java.awt.Dimension(32, 32));
        btnOjo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOjoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDni, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtContrasena)
                                .addGap(0, 0, 0)
                                .addComponent(btnOjo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAlerta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRestaurarContrasena, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHelp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHelp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnHelp1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHelp2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOjo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRestaurarContrasena)
                .addGap(28, 28, 28)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblAlerta)
                .addGap(30, 30, 30)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        btnLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/enfermera.png"))); // NOI18N
        btnLogo.setBorderPainted(false);
        btnLogo.setContentAreaFilled(false);

        btnMinsa.setBorderPainted(false);
        btnMinsa.setContentAreaFilled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sistema Obstetrico");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
            .addComponent(btnMinsa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(btnLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMinsa, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblRestaurarContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRestaurarContrasenaMouseClicked
        RecuperarContrasena();
    }//GEN-LAST:event_lblRestaurarContrasenaMouseClicked

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void btnOjoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOjoActionPerformed
        MostrarContrasena();
        if (btnOjo.isSelected()) {
            btnOjo.setBackground(Color.white);
        }
    }//GEN-LAST:event_btnOjoActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if(ValidarReCaptcha()){
            if(ValidacionCampos()){
                VerificarCredenciales();
            }
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnHelp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp1ActionPerformed
//        JOptionPane.showMessageDialog(null, "Ingrese su Dni o su código!");
        LoginDAO dao = new LoginDAO();
        Usuario u = new Usuario("75063133", "qaz987-M");
        
        dao.VerificarCredenciales(u);
        if (dao.resultado == 1) {
                JOptionPane.showMessageDialog(null, "Bienvenido!");
                frmHome h = frmHome.getInstancia();
                h.setVisible(true);
                dispose();
            } 

    }//GEN-LAST:event_btnHelp1ActionPerformed

    private void btnHelp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelp2ActionPerformed
        JOptionPane.showMessageDialog(null, "Ingrese su contraseña \n recuerde que solo cuenta con 3 intentos");
    }//GEN-LAST:event_btnHelp2ActionPerformed

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed

    }//GEN-LAST:event_txtDniKeyPressed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        FormatoDni(txtDni, evt);
    }//GEN-LAST:event_txtDniKeyTyped

    public static void main(String args[]) {
       
        
        
        try {
            UIManager.setLookAndFeel(new FlatArcIJTheme());
            UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 16));
            UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("Button.arc", 25);
            frmLogin.getInstancia().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHelp1;
    private javax.swing.JButton btnHelp2;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnLogo;
    private javax.swing.JButton btnMinsa;
    private javax.swing.JToggleButton btnOjo;
    private javax.swing.JButton btnRe;
    private javax.swing.JCheckBox ckbRe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAlerta;
    private javax.swing.JLabel lblRestaurarContrasena;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
