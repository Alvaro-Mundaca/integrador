
package GUI;

import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;
import javax.swing.ImageIcon;

public class frmCambiarContrasena extends javax.swing.JFrame {
    
    private static frmCambiarContrasena instancia;
    
    private int indicador, longitud;
    private final char echoChar1,echoChar2;
    private ImageIcon iconoEscaladoMostrar, iconoEscaladoOcultar;
    
    
    private void ValidacionesCheckBox() {

        String contrasena = String.valueOf(jpwContrasena1.getPassword());
        longitud = jpwContrasena1.getPassword().length;
        indicador = longitud*3;
        
        
        if (longitud >= 8) {
            indicador = 25; 
            ckbLongitud.setSelected(true);
            ckbLongitud.setForeground(new java.awt.Color(0,204,0));
        } else {
            ckbLongitud.setSelected(false);
            ckbLongitud.setForeground(Color.red);
        }
        
        
        if (contrasena.matches(".*[A-Z].*")) {
            ckbMayuscula.setSelected(true);
            ckbMayuscula.setForeground(new java.awt.Color(0,204,0));
            if (longitud >= 8) indicador += 25;
        } else {
            ckbMayuscula.setSelected(false);
            ckbMayuscula.setForeground(Color.red);
        }
 
       
        if (contrasena.matches(".*[0-9].*")) {
            ckbNumero.setSelected(true);
            ckbNumero.setForeground(new java.awt.Color(0,204,0));
            if (longitud >= 8) indicador += 25;
        } else {
            ckbNumero.setSelected(false);
            ckbNumero.setForeground(Color.red);
        }
        
        
        if (contrasena.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/].*")) {
            ckbEspecial.setSelected(true);
            ckbEspecial.setForeground(new java.awt.Color(0,204,0));
            if (longitud >= 8) indicador += 25;
        } else {
            ckbEspecial.setSelected(false);
            ckbEspecial.setForeground(Color.red);
            
        }
             
    }
    
    private void NivelDeSeguridad(){
        
        jpbSeguridad.setValue(indicador);
        
        if (jpbSeguridad.getValue()< 25) {
            
            jpbSeguridad.setForeground(Color.red);
            lblNivel.setText("Muy debil");
            
        } else if (jpbSeguridad.getValue()<= 50 && longitud >= 8) {
            
            jpbSeguridad.setForeground(Color.orange);
            lblNivel.setText("Debil");
            
        } else if (jpbSeguridad.getValue()<=75 && longitud >= 8) {
            
            jpbSeguridad.setForeground(Color.yellow);
            lblNivel.setText("Media");
            
        } else if (jpbSeguridad.getValue()<=100 && longitud >= 8) {
            
            jpbSeguridad.setForeground(new java.awt.Color(0,204,0));
            lblNivel.setText("Fuerte");
            
        } else {

            jpbSeguridad.setForeground(Color.red);
            lblNivel.setText("Muy debil");
            
        }
        
    }
    
    private void ConfirmarContrasena(){
        
        if(Arrays.equals(jpwContrasena1.getPassword(), jpwContrasena2.getPassword())){
            
            ckbContrasena.setSelected(true);
            ckbContrasena.setForeground(new java.awt.Color(0,204,0));
            btnAceptar.setEnabled(true);
            
        } else {
            
            ckbContrasena.setSelected(false);
            ckbContrasena.setForeground(Color.red);
            btnAceptar.setEnabled(false);
            
        }    
    }
    
    private void ConfigurarBotonOjo() {
        
        ImageIcon iconoMostrar = new ImageIcon(getClass().getResource("/img/visible.png"));  
        ImageIcon iconoOcultar = new ImageIcon(getClass().getResource("/img/oculto.png"));  
        
        Image imagenEscaladaMostrar = iconoMostrar.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        iconoEscaladoMostrar = new ImageIcon(imagenEscaladaMostrar);
        
        Image imagenEscaladaOcultar = iconoOcultar.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        iconoEscaladoOcultar = new ImageIcon(imagenEscaladaOcultar);
        
    }
    
    private void MostrarContrasena1(){
       
        if (btnOjo1.isSelected()) {
            
            btnOjo1.setIcon(iconoEscaladoMostrar);
            jpwContrasena1.setEchoChar((char) 0);
            
        } else {
            
            btnOjo1.setIcon(iconoEscaladoOcultar);
            jpwContrasena1.setEchoChar(echoChar1);
            
        }
    } 
    
    private void MostrarContrasena2(){
       
        if (btnOjo2.isSelected()) {
            
            btnOjo2.setIcon(iconoEscaladoMostrar);
            jpwContrasena2.setEchoChar((char) 0);
            
        } else {
            
            btnOjo2.setIcon(iconoEscaladoOcultar);
            jpwContrasena2.setEchoChar(echoChar2);
            
        }
    }    
    
    private frmCambiarContrasena() {
        initComponents();
        ConfigurarBotonOjo();
        btnOjo1.setIcon(iconoEscaladoOcultar);
        btnOjo2.setIcon(iconoEscaladoOcultar);
        this.echoChar1 = jpwContrasena1.getEchoChar();
        this.echoChar2 = jpwContrasena2.getEchoChar();
    }
    
    public static frmCambiarContrasena getInstancia(){
        if (instancia == null) {
            instancia = new frmCambiarContrasena();
        }
        return instancia;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jpwContrasena1 = new javax.swing.JPasswordField();
        jpwContrasena2 = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpbSeguridad = new javax.swing.JProgressBar();
        lblNivel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ckbMayuscula = new javax.swing.JCheckBox();
        ckbNumero = new javax.swing.JCheckBox();
        ckbEspecial = new javax.swing.JCheckBox();
        ckbLongitud = new javax.swing.JCheckBox();
        ckbContrasena = new javax.swing.JCheckBox();
        btnAceptar = new javax.swing.JButton();
        btnOjo1 = new javax.swing.JToggleButton();
        btnOjo2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAMBIAR CONTRASEÑA");

        jpwContrasena1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jpwContrasena1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jpwContrasena1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwContrasena1ActionPerformed(evt);
            }
        });
        jpwContrasena1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jpwContrasena1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpwContrasena1KeyTyped(evt);
            }
        });

        jpwContrasena2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jpwContrasena2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jpwContrasena2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwContrasena2ActionPerformed(evt);
            }
        });
        jpwContrasena2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jpwContrasena2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpwContrasena2KeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Ingrese la contraseña");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Repita la contraseña");

        jpbSeguridad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jpbSeguridad.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        lblNivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNivel.setText("Nivel de Seguridad: Ninguno");

        ckbMayuscula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbMayuscula.setForeground(new java.awt.Color(255, 51, 51));
        ckbMayuscula.setText("Contiene al menos una Mayuscula");
        ckbMayuscula.setFocusPainted(false);
        ckbMayuscula.setFocusable(false);
        ckbMayuscula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ckbMayusculaMousePressed(evt);
            }
        });
        ckbMayuscula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbMayusculaActionPerformed(evt);
            }
        });

        ckbNumero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbNumero.setForeground(new java.awt.Color(255, 51, 51));
        ckbNumero.setText("Contiene al menos un Número");
        ckbNumero.setFocusPainted(false);
        ckbNumero.setFocusable(false);
        ckbNumero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ckbNumeroMousePressed(evt);
            }
        });
        ckbNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbNumeroActionPerformed(evt);
            }
        });

        ckbEspecial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbEspecial.setForeground(new java.awt.Color(255, 51, 51));
        ckbEspecial.setText("Contiene al menos un caracter especial");
        ckbEspecial.setFocusPainted(false);
        ckbEspecial.setFocusable(false);
        ckbEspecial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ckbEspecialMousePressed(evt);
            }
        });
        ckbEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbEspecialActionPerformed(evt);
            }
        });

        ckbLongitud.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbLongitud.setForeground(new java.awt.Color(255, 51, 51));
        ckbLongitud.setText("Tiene al menos 8 caracteres");
        ckbLongitud.setFocusPainted(false);
        ckbLongitud.setFocusable(false);
        ckbLongitud.setRequestFocusEnabled(false);
        ckbLongitud.setVerifyInputWhenFocusTarget(false);
        ckbLongitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ckbLongitudMousePressed(evt);
            }
        });
        ckbLongitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbLongitudActionPerformed(evt);
            }
        });

        ckbContrasena.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ckbContrasena.setForeground(new java.awt.Color(255, 51, 51));
        ckbContrasena.setText("Las contraseñas coinciden");
        ckbContrasena.setFocusPainted(false);
        ckbContrasena.setFocusable(false);
        ckbContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ckbContrasenaMousePressed(evt);
            }
        });
        ckbContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbContrasenaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ckbEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbMayuscula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbLongitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ckbContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ckbLongitud)
                .addGap(20, 20, 20)
                .addComponent(ckbMayuscula)
                .addGap(20, 20, 20)
                .addComponent(ckbNumero)
                .addGap(20, 20, 20)
                .addComponent(ckbEspecial)
                .addGap(20, 20, 20)
                .addComponent(ckbContrasena)
                .addGap(25, 25, 25))
        );

        btnAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setEnabled(false);

        btnOjo1.setFocusPainted(false);
        btnOjo1.setMaximumSize(new java.awt.Dimension(32, 32));
        btnOjo1.setMinimumSize(new java.awt.Dimension(32, 32));
        btnOjo1.setPreferredSize(new java.awt.Dimension(32, 32));
        btnOjo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOjo1ActionPerformed(evt);
            }
        });

        btnOjo2.setFocusPainted(false);
        btnOjo2.setMaximumSize(new java.awt.Dimension(32, 32));
        btnOjo2.setMinimumSize(new java.awt.Dimension(32, 32));
        btnOjo2.setPreferredSize(new java.awt.Dimension(32, 32));
        btnOjo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOjo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpbSeguridad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jpwContrasena1)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnOjo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jpwContrasena2)
                                        .addGap(0, 0, 0)
                                        .addComponent(btnOjo2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(218, 218, 218)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpwContrasena1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOjo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpwContrasena2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOjo2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jpbSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNivel)
                .addGap(20, 20, 20)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jpwContrasena1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwContrasena1KeyTyped
        ValidacionesCheckBox();
        NivelDeSeguridad();
    }//GEN-LAST:event_jpwContrasena1KeyTyped

    private void jpwContrasena1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwContrasena1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpwContrasena1ActionPerformed

    private void jpwContrasena2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwContrasena2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpwContrasena2ActionPerformed

    private void ckbLongitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbLongitudMousePressed
        evt.consume();
    }//GEN-LAST:event_ckbLongitudMousePressed

    private void ckbMayusculaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbMayusculaMousePressed
        evt.consume();
    }//GEN-LAST:event_ckbMayusculaMousePressed

    private void ckbNumeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbNumeroMousePressed
        evt.consume();
    }//GEN-LAST:event_ckbNumeroMousePressed

    private void ckbEspecialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbEspecialMousePressed
        evt.consume();
    }//GEN-LAST:event_ckbEspecialMousePressed

    private void ckbLongitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbLongitudActionPerformed
        if (ckbLongitud.isSelected() == true) {
            ckbLongitud.setSelected(false);
        } else {
            ckbLongitud.setSelected(true);
        }
    }//GEN-LAST:event_ckbLongitudActionPerformed

    private void ckbMayusculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbMayusculaActionPerformed
        if (ckbMayuscula.isSelected() == true) {
            ckbMayuscula.setSelected(false);
        } else {
            ckbMayuscula.setSelected(true);
        }
    }//GEN-LAST:event_ckbMayusculaActionPerformed

    private void ckbNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbNumeroActionPerformed
        if (ckbNumero.isSelected() == true) {
            ckbNumero.setSelected(false);
        } else {
            ckbNumero.setSelected(true);
        }
    }//GEN-LAST:event_ckbNumeroActionPerformed

    private void ckbEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbEspecialActionPerformed
        if (ckbEspecial.isSelected() == true) {
            ckbEspecial.setSelected(false);
        } else {
            ckbEspecial.setSelected(true);
        }
    }//GEN-LAST:event_ckbEspecialActionPerformed

    private void jpwContrasena1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwContrasena1KeyReleased
        ValidacionesCheckBox();
        NivelDeSeguridad();
    }//GEN-LAST:event_jpwContrasena1KeyReleased

    private void ckbContrasenaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ckbContrasenaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ckbContrasenaMousePressed

    private void ckbContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbContrasenaActionPerformed
        if (ckbContrasena.isSelected() == true) {
            ckbContrasena.setSelected(false);
        } else {
            ckbContrasena.setSelected(true);
        }
    }//GEN-LAST:event_ckbContrasenaActionPerformed

    private void jpwContrasena2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwContrasena2KeyReleased
        ConfirmarContrasena();
    }//GEN-LAST:event_jpwContrasena2KeyReleased

    private void jpwContrasena2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwContrasena2KeyTyped
        ConfirmarContrasena();
    }//GEN-LAST:event_jpwContrasena2KeyTyped

    private void btnOjo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOjo1ActionPerformed
        MostrarContrasena1();
    }//GEN-LAST:event_btnOjo1ActionPerformed

    private void btnOjo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOjo2ActionPerformed
        MostrarContrasena2();
    }//GEN-LAST:event_btnOjo2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCambiarContrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCambiarContrasena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JToggleButton btnOjo1;
    private javax.swing.JToggleButton btnOjo2;
    private javax.swing.JCheckBox ckbContrasena;
    private javax.swing.JCheckBox ckbEspecial;
    private javax.swing.JCheckBox ckbLongitud;
    private javax.swing.JCheckBox ckbMayuscula;
    private javax.swing.JCheckBox ckbNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jpbSeguridad;
    private javax.swing.JPasswordField jpwContrasena1;
    private javax.swing.JPasswordField jpwContrasena2;
    private javax.swing.JLabel lblNivel;
    // End of variables declaration//GEN-END:variables
}
