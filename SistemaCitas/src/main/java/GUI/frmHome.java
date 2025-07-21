package GUI;

import static DAO.LoginDAO.*;
import Strategy.*;
import Util.*;
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import static java.awt.Toolkit.getDefaultToolkit;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.medicons.Medicons;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.unicons.UniconsSolid;



public class frmHome extends JFrame implements Formato,Confirmacion{
    
    private static frmHome instancia;
    
    public static void ConfigurarLogoMinsa() {
        
        ImageIcon iconoOriginal = new ImageIcon(frmHome.class.getResource("/img/Minsa.png"));  
        
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(235, 64, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        
        lblMinsa.setIcon(iconoEscalado);
    }
    
    private void ConfigurarBotones(){
        FontIcon pac = FontIcon.of(Medicons.WOMENS_HEALTH,32,Color.WHITE);
        FontIcon obs = FontIcon.of(Medicons.CARE_STAFF_AREA,32,Color.WHITE);
        FontIcon hom = FontIcon.of(Material2AL.HOME,32,Color.WHITE);
        FontIcon cit = FontIcon.of(Material2AL.EVENT,32,Color.WHITE);
        FontIcon pro = FontIcon.of(Material2AL.FACT_CHECK,32,Color.WHITE);
        FontIcon rep = FontIcon.of(UniconsSolid.GRAPH_BAR,32,Color.WHITE);
        
        
        btnPacientes.setIcon(pac);
        btnPacientes.setIconTextGap(8);
        
        btnObstetras.setIcon(obs);
        btnObstetras.setIconTextGap(8);
        
        btnInicio.setIcon(hom);
        btnInicio.setIconTextGap(8);
        
        btnCitas.setIcon(cit);
        btnCitas.setIconTextGap(8);
        
        btnProgramas.setIcon(pro);
        btnProgramas.setIconTextGap(8);

        btnReportes.setIcon(rep);
        btnReportes.setIconTextGap(8);
    }
    
    private void Identificador(){
        FontIcon icon = FontIcon.of(FontAwesome.CIRCLE, 24, new Color(0, 200, 0));
        lblUsuario.setText("Conectado: " + user + "          Rol: ");
        lblUsuario.setIcon(icon);
        lblUsuario.setIconTextGap(5);
        lblRol.setText(tipo);
        
        if (tipo.equals("USER")) {
            lblRol.setForeground(new Color(191, 137, 112));
        } else if (tipo.equals("ADMIN")) {
            lblRol.setForeground(new Color(227, 228, 229));
        } else {
//            lblRol.setForeground(new Color(212, 175, 55));
            lblRol.setForeground(new Color(239, 184, 16));

        }
        
    }
    
    private void Inicio(){
        frmInicio.getInstancia().EliminarInstacia();
        FormatearJInternalFrame(frmInicio.getInstancia());
    }
    
    private void Obstetras(){
        FormatearJInternalFrame(frmBase.crearInstancia(new GestorObstetra()));      
    }
    
    private void Pacientes(){
        FormatearJInternalFrame(frmBase.crearInstancia(new GestorPaciente()));
    }
    
    private void Citas(){
        frmCitas.getInstancia(new GestorCitas()).EliminarInstacia();
        FormatearJInternalFrame(frmCitas.getInstancia(new GestorCitas()));
    }
    
    private void Programas(){
        FormatearJInternalFrame(frmBase.crearInstancia(new GestorPrograma()));
    }
    
    private void Reportes(){
        FormatearJInternalFrame(new frmReportes());
    }
    
    private void CerrarSesion(){
        if (ConfirmacionCerrarSesion()) {
            dispose();
            EliminarInstacia();
            frmLogin.getInstancia().setVisible(true);
        }
    }
    
    private void Menu(JButton boton){      
        if (tipo.equals("SUPER_ADMIN")) {
            jpmConfiguracion.add(jcbTesting);
        } else {
            jpmConfiguracion.remove(jcbTesting);
        }
        
        jpmConfiguracion.add(jmiActualizarDatos);
        jpmConfiguracion.add(jmiCambiarClave);
        
        jpmConfiguracion.show(boton, 0, boton.getHeight());     
    }
    
    private void CambiarContrasena(){
        frmCambiarContrasena.getInstancia().setVisible(true);
    }
    
    private void AjustarTamaño(){
        GraphicsConfiguration gc = getGraphicsConfiguration();
        Rectangle screenBounds = gc.getBounds();
        Insets screenInsets = getDefaultToolkit().getScreenInsets(gc);

        int x = screenBounds.x;
        int y = screenBounds.y + screenInsets.top; 
        int width = screenBounds.width;
        int height = screenBounds.height - screenInsets.top - screenInsets.bottom;
        
        setBounds(-7, y, width + 15, height + 10);
    }
    
    private void controlPermisos(){
        if (tipo.equals("USER")) {
//            btnObstetras.setVisible(false);
            jpBotones.remove(btnObstetras);
        }
    }
    
    public static void EliminarInstacia(){
        if (instancia != null){
            instancia.dispose();
            instancia = null;
        }
    }
    
    
    
    private frmHome() {   
        initComponents(); 
        controlPermisos();
        AjustarTamaño();
        ConfigurarLogoMinsa();
        ConfigurarBotones();
        Identificador();
        Inicio();
        OpcionesDesplegables.capturarDimension();
        lblMinsa.setToolTipText("Contraer");
    }
    
    public static frmHome getInstancia(){
        if (instancia == null) {
            instancia = new frmHome();
        }
        return instancia;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmConfiguracion = new javax.swing.JPopupMenu();
        jmiCambiarClave = new javax.swing.JMenuItem();
        jmiActualizarDatos = new javax.swing.JMenuItem();
        jcbTesting = new javax.swing.JCheckBoxMenuItem();
        jpHerramientas = new javax.swing.JPanel();
        jpHerramientasR = new javax.swing.JPanel();
        btnConfiguracion = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jpConectado = new javax.swing.JPanel();
        lblRol = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jpOpciones = new javax.swing.JPanel();
        jpBotones = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnPacientes = new javax.swing.JButton();
        btnObstetras = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnProgramas = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();
        jpTitulo = new javax.swing.JPanel();
        lblMinsa = new javax.swing.JLabel();
        jpInferior = new javax.swing.JPanel();
        jdpPrincipal = new javax.swing.JDesktopPane();

        jpmConfiguracion.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jpmConfiguracion.setDoubleBuffered(true);
        jpmConfiguracion.setMaximumSize(new java.awt.Dimension(140, 60));
        jpmConfiguracion.setMinimumSize(new java.awt.Dimension(140, 60));
        jpmConfiguracion.setPreferredSize(new java.awt.Dimension(140, 60));

        jmiCambiarClave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmiCambiarClave.setText("Cambiar Contraseña");
        jmiCambiarClave.setDoubleBuffered(true);
        jmiCambiarClave.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jmiCambiarClave.setIconTextGap(0);
        jmiCambiarClave.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jmiCambiarClave.setPreferredSize(new java.awt.Dimension(140, 30));
        jmiCambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCambiarClaveActionPerformed(evt);
            }
        });
        jpmConfiguracion.add(jmiCambiarClave);

        jmiActualizarDatos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jmiActualizarDatos.setText("Actualizar Datos");
        jmiActualizarDatos.setDoubleBuffered(true);
        jmiActualizarDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jmiActualizarDatos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jmiActualizarDatos.setIconTextGap(0);
        jmiActualizarDatos.setPreferredSize(new java.awt.Dimension(140, 30));
        jpmConfiguracion.add(jmiActualizarDatos);

        jcbTesting.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbTesting.setSelected(true);
        jcbTesting.setText("Testing");
        jcbTesting.setToolTipText("");
        jpmConfiguracion.add(jcbTesting);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEMA CITAS");
        setResizable(false);

        jpHerramientas.setBackground(new java.awt.Color(51, 51, 51));
        jpHerramientas.setLayout(new java.awt.BorderLayout());

        jpHerramientasR.setBackground(new java.awt.Color(51, 51, 51));

        btnConfiguracion.setBackground(new java.awt.Color(51, 51, 51));
        btnConfiguracion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConfiguracion.setForeground(new java.awt.Color(255, 255, 255));
        btnConfiguracion.setText("Configuración");
        btnConfiguracion.setBorderPainted(false);
        btnConfiguracion.setContentAreaFilled(false);
        btnConfiguracion.setDoubleBuffered(true);
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(51, 51, 51));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar Sesión");
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setDoubleBuffered(true);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpHerramientasRLayout = new javax.swing.GroupLayout(jpHerramientasR);
        jpHerramientasR.setLayout(jpHerramientasRLayout);
        jpHerramientasRLayout.setHorizontalGroup(
            jpHerramientasRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHerramientasRLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnConfiguracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );
        jpHerramientasRLayout.setVerticalGroup(
            jpHerramientasRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHerramientasRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpHerramientasRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfiguracion)
                    .addComponent(btnCerrar))
                .addGap(3, 3, 3))
        );

        jpHerramientas.add(jpHerramientasR, java.awt.BorderLayout.LINE_END);

        jpConectado.setBackground(new java.awt.Color(51, 51, 51));

        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRol.setForeground(new java.awt.Color(255, 255, 255));
        lblRol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRol.setText("Rol: user");
        lblRol.setAlignmentY(0.0F);
        lblRol.setDoubleBuffered(true);
        lblRol.setIconTextGap(5);
        lblRol.setMaximumSize(new java.awt.Dimension(371, 30));
        lblRol.setMinimumSize(new java.awt.Dimension(371, 30));
        lblRol.setPreferredSize(new java.awt.Dimension(371, 30));

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuario.setText("Conectado: Obstetra ");
        lblUsuario.setAlignmentY(0.0F);
        lblUsuario.setDoubleBuffered(true);
        lblUsuario.setIconTextGap(5);
        lblUsuario.setMaximumSize(new java.awt.Dimension(371, 30));
        lblUsuario.setMinimumSize(new java.awt.Dimension(371, 30));
        lblUsuario.setPreferredSize(new java.awt.Dimension(371, 30));

        javax.swing.GroupLayout jpConectadoLayout = new javax.swing.GroupLayout(jpConectado);
        jpConectado.setLayout(jpConectadoLayout);
        jpConectadoLayout.setHorizontalGroup(
            jpConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConectadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpConectadoLayout.setVerticalGroup(
            jpConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConectadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConectadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpHerramientas.add(jpConectado, java.awt.BorderLayout.WEST);

        jpOpciones.setBackground(new java.awt.Color(0, 204, 204));
        jpOpciones.setToolTipText("");
        jpOpciones.setLayout(new java.awt.BorderLayout());

        jpBotones.setBackground(new java.awt.Color(0, 118, 171));
        jpBotones.setLayout(new java.awt.GridLayout(6, 1, 20, 30));

        btnInicio.setBackground(new java.awt.Color(0, 118, 171));
        btnInicio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setText("Inicio");
        btnInicio.setToolTipText("INICIO");
        btnInicio.setBorder(null);
        btnInicio.setFocusPainted(false);
        btnInicio.setFocusable(false);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jpBotones.add(btnInicio);

        btnPacientes.setBackground(new java.awt.Color(0, 118, 171));
        btnPacientes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        btnPacientes.setText("Pacientes");
        btnPacientes.setToolTipText("PACIENTES");
        btnPacientes.setBorder(null);
        btnPacientes.setFocusPainted(false);
        btnPacientes.setFocusable(false);
        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacientesActionPerformed(evt);
            }
        });
        jpBotones.add(btnPacientes);

        btnObstetras.setBackground(new java.awt.Color(0, 118, 171));
        btnObstetras.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnObstetras.setForeground(new java.awt.Color(255, 255, 255));
        btnObstetras.setText("Obstetras");
        btnObstetras.setToolTipText("OBSTETRAS");
        btnObstetras.setBorder(null);
        btnObstetras.setFocusPainted(false);
        btnObstetras.setFocusable(false);
        btnObstetras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObstetrasActionPerformed(evt);
            }
        });
        jpBotones.add(btnObstetras);

        btnCitas.setBackground(new java.awt.Color(0, 118, 171));
        btnCitas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCitas.setForeground(new java.awt.Color(255, 255, 255));
        btnCitas.setText("Citas");
        btnCitas.setToolTipText("CITAS");
        btnCitas.setBorder(null);
        btnCitas.setFocusPainted(false);
        btnCitas.setFocusable(false);
        btnCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasActionPerformed(evt);
            }
        });
        jpBotones.add(btnCitas);

        btnProgramas.setBackground(new java.awt.Color(0, 118, 171));
        btnProgramas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnProgramas.setForeground(new java.awt.Color(255, 255, 255));
        btnProgramas.setText("Programas");
        btnProgramas.setToolTipText("PROGRAMAS");
        btnProgramas.setBorder(null);
        btnProgramas.setFocusPainted(false);
        btnProgramas.setFocusable(false);
        btnProgramas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgramasActionPerformed(evt);
            }
        });
        jpBotones.add(btnProgramas);

        btnReportes.setBackground(new java.awt.Color(0, 118, 171));
        btnReportes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setText("Reportes");
        btnReportes.setToolTipText("REPORTES");
        btnReportes.setBorder(null);
        btnReportes.setFocusPainted(false);
        btnReportes.setFocusable(false);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        jpBotones.add(btnReportes);

        jpOpciones.add(jpBotones, java.awt.BorderLayout.CENTER);

        jpTitulo.setBackground(new java.awt.Color(0, 118, 171));
        jpTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jpTituloMouseEntered(evt);
            }
        });

        lblMinsa.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblMinsa.setForeground(new java.awt.Color(255, 255, 255));
        lblMinsa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinsa.setText("Sistema de Citas");
        lblMinsa.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblMinsa.setDoubleBuffered(true);
        lblMinsa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMinsa.setIconTextGap(10);
        lblMinsa.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        lblMinsa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinsaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinsaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMinsa, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTituloLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(lblMinsa, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jpOpciones.add(jpTitulo, java.awt.BorderLayout.PAGE_START);

        jpInferior.setBackground(new java.awt.Color(0, 118, 171));

        javax.swing.GroupLayout jpInferiorLayout = new javax.swing.GroupLayout(jpInferior);
        jpInferior.setLayout(jpInferiorLayout);
        jpInferiorLayout.setHorizontalGroup(
            jpInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jpInferiorLayout.setVerticalGroup(
            jpInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jpOpciones.add(jpInferior, java.awt.BorderLayout.PAGE_END);

        jdpPrincipal.setDoubleBuffered(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jpOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdpPrincipal)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jdpPrincipal))
            .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnObstetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObstetrasActionPerformed
        Obstetras();
    }//GEN-LAST:event_btnObstetrasActionPerformed

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
        Pacientes();
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        Inicio();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        CerrarSesion();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracionActionPerformed
        Menu(btnConfiguracion);
    }//GEN-LAST:event_btnConfiguracionActionPerformed

    private void jmiCambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCambiarClaveActionPerformed
        CambiarContrasena();
    }//GEN-LAST:event_jmiCambiarClaveActionPerformed

    private void btnProgramasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgramasActionPerformed
        Programas();
    }//GEN-LAST:event_btnProgramasActionPerformed

    private void btnCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasActionPerformed
        Citas();
    }//GEN-LAST:event_btnCitasActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        Reportes();
    }//GEN-LAST:event_btnReportesActionPerformed

    private void jpTituloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpTituloMouseEntered
   
    }//GEN-LAST:event_jpTituloMouseEntered

    private void lblMinsaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinsaMouseExited

    }//GEN-LAST:event_lblMinsaMouseExited

    private void lblMinsaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinsaMouseClicked
        if(jpOpciones.getWidth()<260){
            OpcionesDesplegables.animarMostrar();
            lblMinsa.setToolTipText("Contraer");
        } else {
            OpcionesDesplegables.animarOcultar();
            lblMinsa.setToolTipText("Expandir");
        }
    }//GEN-LAST:event_lblMinsaMouseClicked

    public static void main(String args[]) {
        
        try {
            
           UIManager.setLookAndFeel(new FlatArcIJTheme());
           new frmHome().setVisible(true);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    public static javax.swing.JButton btnCitas;
    private javax.swing.JButton btnConfiguracion;
    public static javax.swing.JButton btnInicio;
    public static javax.swing.JButton btnObstetras;
    public static javax.swing.JButton btnPacientes;
    public static javax.swing.JButton btnProgramas;
    public static javax.swing.JButton btnReportes;
    public static javax.swing.JCheckBoxMenuItem jcbTesting;
    public static javax.swing.JDesktopPane jdpPrincipal;
    private javax.swing.JMenuItem jmiActualizarDatos;
    private javax.swing.JMenuItem jmiCambiarClave;
    public static javax.swing.JPanel jpBotones;
    private javax.swing.JPanel jpConectado;
    private javax.swing.JPanel jpHerramientas;
    private javax.swing.JPanel jpHerramientasR;
    private javax.swing.JPanel jpInferior;
    public static javax.swing.JPanel jpOpciones;
    public static javax.swing.JPanel jpTitulo;
    private javax.swing.JPopupMenu jpmConfiguracion;
    public static javax.swing.JLabel lblMinsa;
    public static javax.swing.JLabel lblRol;
    public static javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables
}
